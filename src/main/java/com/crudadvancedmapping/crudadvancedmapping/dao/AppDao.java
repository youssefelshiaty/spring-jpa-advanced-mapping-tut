package com.crudadvancedmapping.crudadvancedmapping.dao;

import com.crudadvancedmapping.crudadvancedmapping.entity.Course;
import com.crudadvancedmapping.crudadvancedmapping.entity.Instructor;
import com.crudadvancedmapping.crudadvancedmapping.entity.InstructorDetail;

import java.util.List;

public interface AppDao {
    void saveInstructor(Instructor instructor);

    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    //__________
    InstructorDetail findInstructorDetailsById(int id);

    void deleteInstructorDetailsById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findCourseInstructor(int courseId);

    Course findCourseById(int courseId);

    Instructor findInstructorByIdJoinFetch(int id);


}
