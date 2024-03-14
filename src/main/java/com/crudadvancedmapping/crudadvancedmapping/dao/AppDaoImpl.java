package com.crudadvancedmapping.crudadvancedmapping.dao;

import com.crudadvancedmapping.crudadvancedmapping.entity.Course;
import com.crudadvancedmapping.crudadvancedmapping.entity.Instructor;
import com.crudadvancedmapping.crudadvancedmapping.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {
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
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor dbinstructor = findInstructorById(id);
        if (dbinstructor == null) return;
        //This will delete also the InstructorDetails record as we define cascade type .ALL
        entityManager.remove(dbinstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailsById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int id) {
        InstructorDetail dbInstructorDet = findInstructorDetailsById(id);
        if (dbInstructorDet == null) return;
        //There to remove the associated obj ref
        dbInstructorDet.getInstructor().setInstructorDetail(null);
        //This will delete also the Instructor record as we define cascade type .ALL
        entityManager.remove(dbInstructorDet);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public Instructor findCourseInstructor(int courseId) {
        Course dbCourse = findCourseById(courseId);
        return entityManager.find(Instructor.class, dbCourse.getInstructor().getId());
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "join fetch i.courses " +
                        //"join fetch i.instructorDetail "+
                        "where i.id = :data", Instructor.class
        );
        query.setParameter("data", id);
        return query.getSingleResult();
    }
}
