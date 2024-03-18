package com.crudadvancedmapping.crudadvancedmapping.dao;

import com.crudadvancedmapping.crudadvancedmapping.entity.Course;
import com.crudadvancedmapping.crudadvancedmapping.entity.Instructor;
import com.crudadvancedmapping.crudadvancedmapping.entity.InstructorDetail;
import com.crudadvancedmapping.crudadvancedmapping.entity.Student;

import java.util.List;

public interface AppDao {
    void saveInstructor(Instructor instructor);

    Instructor findInstructorById(int id);
    void removeInstructor(int id);

    //__________
    InstructorDetail findInstructorDetailsById(int id);

    void deleteInstructorDetailsById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findCourseInstructor(int courseId);

    Course findCourseById(int courseId);

    Instructor findInstructorByIdJoinFetch(int id);

    void updateInstructor(Instructor instructor);

    void updateCourse(Course course);

    void deleteCourse(int id);

    void saveCourse(Course course);

    Course findCourseWithReviews(int id);

    Course findCourseAndStudentsByCourseId(int id);

    Student findStudentAndCoursesByStudentId(int id);

    void updateStudent(Student student);

}
