package com.crudadvancedmapping.crudadvancedmapping.dao;

import com.crudadvancedmapping.crudadvancedmapping.entity.Instructor;
import com.crudadvancedmapping.crudadvancedmapping.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDaoImpl implements AppDao{
    private final EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) {
        //This will also save the InstructorDetails obj as we define cascade type .ALL
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        //This will fetch also the InstructorDetails record as the default behavior of @OneToOne fetch type is eager
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor dbinstructor = findInstructorById(id);
        if(dbinstructor==null)return;
        //This will delete also the InstructorDetails record as we define cascade type .ALL
        entityManager.remove(dbinstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailsById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int id) {
        InstructorDetail dbInstructorDet = findInstructorDetailsById(id);
        if(dbInstructorDet==null)return;
        //there to remove the associated obj ref
        dbInstructorDet.getInstructor().setInstructorDetail(null);
        //This will delete also the Instructor record as we define cascade type .ALL

        entityManager.remove(dbInstructorDet);
    }
}
