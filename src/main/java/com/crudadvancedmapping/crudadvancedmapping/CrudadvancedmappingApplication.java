package com.crudadvancedmapping.crudadvancedmapping;

import com.crudadvancedmapping.crudadvancedmapping.dao.AppDao;
import com.crudadvancedmapping.crudadvancedmapping.entity.Course;
import com.crudadvancedmapping.crudadvancedmapping.entity.Instructor;
import com.crudadvancedmapping.crudadvancedmapping.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner -> {
			//createInstructor(appDao);
			//findInstructor(appDao);
			//deleteInstructor(appDao);
			//findInstructorDetails(appDao);
			//deleteInstructorDetails(appDao);

			//createInstructorWithCourse(appDao);

			//findInstructorCourses(appDao);
			//findCourseInstructor(appDao);
			findInstructorWithCourse(appDao);

		};
	}

	private void findCourseInstructor(AppDao appDao) {
		int theCourseId = 10;
		Instructor instructor = appDao.findCourseInstructor(theCourseId);
		System.out.print("instructor is: "+instructor);
	}

	private void findInstructorCourses(AppDao appDao) {
		int instructorId = 1;
		List<Course> courses = appDao.findCoursesByInstructorId(instructorId);
		System.out.print("courses is: "+courses);
	}

	private void findInstructorWithCourse(AppDao appDao) {
		int theId = 1;
		Instructor instructor = appDao.findInstructorByIdJoinFetch(theId);
		System.out.print(instructor);
		System.out.print("instructor details: "+instructor.getInstructorDetail());
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
		System.out.print("The Instructor " + instructor.getFirstName() + " courses is: "+instructor.getCourses());

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
		int theId = 3;
		appDao.deleteInstructorById(theId);
		System.out.print("\nDone Delete!! " + theId);
	}

	private void findInstructor(AppDao appDao) {
		int theId = 1;
		Instructor instructor = appDao.findInstructorById(theId);
		if(instructor==null)return;
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
