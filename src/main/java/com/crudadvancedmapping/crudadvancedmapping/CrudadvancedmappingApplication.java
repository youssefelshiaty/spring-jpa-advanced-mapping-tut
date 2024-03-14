package com.crudadvancedmapping.crudadvancedmapping;

import com.crudadvancedmapping.crudadvancedmapping.dao.AppDao;
import com.crudadvancedmapping.crudadvancedmapping.entity.Instructor;
import com.crudadvancedmapping.crudadvancedmapping.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			findInstructorDetails(appDao);
			//deleteInstructorDetails(appDao);
		};
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
