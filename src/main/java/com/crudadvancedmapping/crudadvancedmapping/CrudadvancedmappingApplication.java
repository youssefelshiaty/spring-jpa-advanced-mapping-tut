package com.crudadvancedmapping.crudadvancedmapping;

import com.crudadvancedmapping.crudadvancedmapping.dao.AppDao;
import com.crudadvancedmapping.crudadvancedmapping.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

///Default relations fetch types
//@OneToOne is EAGER
//@OneToMany is LAZY
//@ManyToOne is EAGER
//@ManyToMany is LAZY
@SpringBootApplication
public class CrudadvancedmappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudadvancedmappingApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) {
        return runner -> {
            //createInstructor(appDao);
            //findInstructor(appDao);
            //deleteInstructor(appDao);
            //findInstructorDetails(appDao);
            //deleteInstructorDetails(appDao);

            //createInstructorWithCourse(appDao);

            //findInstructorCourses(appDao);
            //findCourseInstructor(appDao);
            //findInstructorWithCourse(appDao);

            //updateInstructor(appDao);

            //findCourseById(appDao);
            //updateCourse(appDao);

            //deleteInstructor(appDao);

            //createCourseAndReview(appDao);

            //findCourseWithReviews(appDao);

            deleteCourse(appDao);

            //createCourseAndStudent(appDao);
            //findCourseAndStudentsByCourseId(appDao);
            //findStudentAndCoursesByStudentId(appDao);

            //addMoreCoursesForStudent(appDao);
        };
    }

    private void addMoreCoursesForStudent(AppDao appDao) {
        int theId = 2;
        Student student = appDao.findStudentAndCoursesByStudentId(theId);

        Course course1 = new Course("GitHub");
        Course course2 = new Course("GameDev");

        student.addCourse(course1);
        student.addCourse(course2);


        appDao.updateStudent(student);
        System.out.print("Done addMoreCoursesForStudent: "+student);

    }

    private void findStudentAndCoursesByStudentId(AppDao appDao) {
        int theId = 1;
        Student student = appDao.findStudentAndCoursesByStudentId(theId);

        System.out.print("Done findStudentAndCoursesByStudentId: "+student);
    }

    private void findCourseAndStudentsByCourseId(AppDao appDao) {
        int theId = 10;
        Course course = appDao.findCourseAndStudentsByCourseId(theId);

        System.out.print("Done findCourseAndStudentsByCourseId: "+course);

    }

    private void createCourseAndStudent(AppDao appDao) {
        Course course = new Course("Some Course");
        Student student = new Student("Yusuf", "Hany", "yusu4@gmail.com");
        Student student2 = new Student("Yusuff", "Hanyy", "yusu44@gmail.com");

        course.addStudent(student);
        course.addStudent(student2);

        //student.addCourse(course);

        appDao.saveCourse(course);

        System.out.print("Done save course: "+course);

    }

    private void deleteCourse(AppDao appDao) {
        appDao.deleteCourse(10);
        System.out.print("course deleted: ");

    }

    private void findCourseWithReviews(AppDao appDao) {
        Course course = appDao.findCourseWithReviews(10);
        System.out.print("course is: " + course);

    }

    private void createCourseAndReview(AppDao appDao) {
        Course course = new Course("Flutter");
        Review review = new Review("Wow!!");
        Review review2 = new Review("BAD!!");

        course.addReview(review);
        course.addReview(review2);

        appDao.saveCourse(course);
        System.out.print("course and rev saved!! " + course);

    }

    private void updateCourse(AppDao appDao) {
        Course dbCourse = appDao.findCourseById(10);
        dbCourse.setTitle("Java spring AOP");
        appDao.updateCourse(dbCourse);
    }

    private void findCourseById(AppDao appDao) {
        Course course = appDao.findCourseById(10);
        System.out.print("course is: " + course);

    }

    private void updateInstructor(AppDao appDao) {
        int theId = 1;
        Instructor instructor = appDao.findInstructorById(theId);
        System.out.print("instructorIs: " + instructor.getFirstName() + " details is: " + instructor.getInstructorDetail());

        instructor.setLastName("Haha");
        appDao.updateInstructor(instructor);
        System.out.print("instructor updated!!: " + instructor);

    }

    private void findCourseInstructor(AppDao appDao) {
        int theCourseId = 10;
        Instructor instructor = appDao.findCourseInstructor(theCourseId);
        System.out.print("instructor is: " + instructor);
    }

    private void findInstructorCourses(AppDao appDao) {
        int instructorId = 1;
        List<Course> courses = appDao.findCoursesByInstructorId(instructorId);
        System.out.print("courses is: " + courses);
    }

    private void findInstructorWithCourse(AppDao appDao) {
        int theId = 1;
        Instructor instructor = appDao.findInstructorByIdJoinFetch(theId);
        System.out.print(instructor);
        System.out.print("instructor details: " + instructor.getInstructorDetail());
    }

    private void createInstructorWithCourse(AppDao appDao) {
        Instructor instructor = new Instructor(
                "Mohammed",
                "Ahmed",
                "moh@gmail.com"
        );
        InstructorDetail instructorDetail = new InstructorDetail(
                "youtube.channel.mohammed.com",
                "Game!"
        );
        instructor.setInstructorDetail(instructorDetail);

        //add course
        Course course = new Course("Java Spring");
        Course course2 = new Course("Java Spring Advanced");

        instructor.addCourse(course);
        instructor.addCourse(course2);

        appDao.saveInstructor(instructor);

        System.out.print("\nDone Save Instructor!! " + instructor);
        System.out.print("The Instructor " + instructor.getFirstName() + " courses is: " + instructor.getCourses());

    }

    private void deleteInstructorDetails(AppDao appDao) {
        int theId = 5;
        appDao.deleteInstructorDetailsById(theId);
        System.out.print("\nDone Delete!! " + theId);
    }

    private void findInstructorDetails(AppDao appDao) {
        int theId = 1;
        InstructorDetail instructorDetail = appDao.findInstructorDetailsById(theId);
        System.out.print("\nDone Find InstructorDetails!! " + instructorDetail);
    }

    private void deleteInstructor(AppDao appDao) {
        int theId = 1;
        appDao.removeInstructor(theId);

        System.out.print("\nInstructor deleted ");

    }

    private void findInstructor(AppDao appDao) {
        int theId = 1;
        Instructor instructor = appDao.findInstructorById(theId);
        if (instructor == null) return;
        System.out.print("\nDone find!! " + instructor);
    }

    private void createInstructor(AppDao appDao) {
        //create the Instructor obj
        Instructor instructor = new Instructor(
                "Ahmed",
                "Hany",
                "ahmedhany@gmail.com"
        );
        //create Instructor details obj
        InstructorDetail instructorDetail = new InstructorDetail(
                "youtube.channel.ahmed.com",
                "Playing!"
        );
        //associate instructorDetails  to instructor obj
        System.out.print("\nSaving...");
        instructor.setInstructorDetail(instructorDetail);
        //save Instructor
        appDao.saveInstructor(instructor);
        System.out.print("\nDone Saving!!! " + instructor);

    }

}
