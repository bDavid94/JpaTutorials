package com.in28minutes.jpa.hibernate.demo;

import com.in28minutes.jpa.hibernate.demo.entity.*;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.demo.repository.PassportRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
    private StudentRepository studentRepository;
	@Autowired
	private PassportRepository passportRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
//        studentRepository.saveStudentWithPassport();
//		courseRepository.addReviewForCourse(10003L, new Review("bad course", 2));
//		studentRepository.insertStudentAndCourse();
//		Course course = courseRepository.findById(2l);
//		logger.info("----------------------------------------------------------");
//		logger.info("student's courses -> {}", course.getStudents());
//		logger.info("----------------------------------------------------------");
//		Student student = new Student("boss");
//		Passport passport = new Passport("pasaportul lui boss");
//		studentRepository.save(student);
//		passportRepository.save(passport);
//		student.setPassport(passport);
//		studentRepository.update(student);
//		passport.setStudent(student);
//		passportRepository.update(passport);
//		logger.info("----------------------------------------------------------");
//		logger.info("student's courses -> {}", passport.getStudent());
//		logger.info("----------------------------------------------------------");

		employeeRepository.add(new FullTimeEmployee("Jack", new BigDecimal(10000)));
		employeeRepository.add(new PartTimeEmployee("Ana", new BigDecimal(50)));
//		logger.info("------------------------------------------");
//		logger.info("all employees -> {}", employeeRepository.findAll());
//		logger.info("------------------------------------------");

	}
}
