package com.crudadvancedmapping.crudadvancedmapping.dao;

import com.crudadvancedmapping.crudadvancedmapping.entity.Instructor;
import com.crudadvancedmapping.crudadvancedmapping.entity.InstructorDetail;

public interface AppDao {
    void saveInstructor(Instructor instructor);

    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    //__________
    InstructorDetail findInstructorDetailsById(int id);

    void deleteInstructorDetailsById(int id);

}
