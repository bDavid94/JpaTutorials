package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StudentRepositoryTest {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EntityManager entityManager;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	@Transactional
	public void findById_basic() {
		Student student = studentRepository.findById(20001L);
		logger.info(student.getName());
		logger.info(student.getPassport().toString());
	}

	@Test
	public void retrieveStudentAndCourse() {
		Student student = studentRepository.findById(20001L);
		logger.info("student -> {}", student);
		logger.info("student courses -> {}", student.getCourses());
	}

	@Test
	public void jpqlTest() {
		TypedQuery<Course> query = entityManager.createQuery(
				"select c from Course c where c.students is empty", Course.class);
		logger.info("--------------------------------------------");
		logger.info("courses without students -> {}", query.getResultList());
		logger.info("--------------------------------------------");

	}

	@Test
	public void jpqlTestCourses_with_atLeast_2Students() {
		TypedQuery<Course> query = entityManager.createQuery(
				"select c from Course c where size(c.students) >= 2", Course.class);
		logger.info("--------------------------------------------");
		logger.info("courses with at least 2 students -> {}", query.getResultList());
		logger.info("--------------------------------------------");

	}

	@Test
	public void jpqlTestCourses_ordered_by_students_number() {
		TypedQuery<Course> query = entityManager.createQuery(
				"select c from Course c order by size(c.students) desc", Course.class);
		logger.info("--------------------------------------------");
		logger.info("courses ordered by students number -> {}", query.getResultList());
		logger.info("--------------------------------------------");

	}
	@Test
	public void jpqlTest_students_with_passport_with_like() {
		TypedQuery<Student> query = entityManager.createQuery(
				"select s from Student s where s.passport.number like '%11%'", Student.class);
		logger.info("--------------------------------------------");
		logger.info("students: -> {}", query.getResultList());
		logger.info("--------------------------------------------");

	}

	@Test
	public void jpql_join() {
		Query query = entityManager.createQuery("select c, s from Course c join c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("--------------------------------------------");
		for(Object[] result: resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
		logger.info("--------------------------------------------");

	}

	@Test
	public void jpql_left_join() {
		Query query = entityManager.createQuery("select c, s from Course c left join c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("--------------------------------------------");
		for(Object[] result: resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
		logger.info("--------------------------------------------");

	}

	@Test
	public void jpql_cross_join() {
		Query query = entityManager.createQuery("select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("--------------------------------------------");
		for(Object[] result: resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
		logger.info("--------------------------------------------");
	}

	@Test
	public void criteriaQuery_list_all_courses() {
		// Select c from Course c

		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query

		Root<Course> courseRoot = criteriaQuery.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder

		// 4. Add Predicates etc to the Criteria Query

		// 5. Build the TypedQuery using the entity manager and criteria query

		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
		logger.info("--------------------------------------------");
		logger.info("result -> {}", query.getResultList());
		logger.info("--------------------------------------------");

	}

	@Test
	public void criteria_all_courses_having_100Steps() {
		//	select c from Course c where name like `%100 Steps`
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		Predicate like = criteriaBuilder.like(courseRoot.get("name"), "%50 Steps%");
		criteriaQuery.where(like);
		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
		List<Course> courses = query.getResultList();
		System.out.println("************************************************************************************************************************");
		logger.info("--------------------------------------------");
		logger.info("result -> {}", courses);
		logger.info("--------------------------------------------");
	}

	@Test
	public void criteria_all_courses_without_students() {
		//	select c from Course c where name c.students is empty
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		Predicate studentsIsEmpty = criteriaBuilder.isEmpty(courseRoot.get("students"));
		criteriaQuery.where(studentsIsEmpty);
		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
		List<Course> courses = query.getResultList();
		System.out.println("************************************************************************************************************************");
		logger.info("--------------------------------------------");
		logger.info("result -> {}", courses);
		logger.info("--------------------------------------------");
	}

	@Test
	public void criteria_join() {
		//	select c from Course c, Student s join c.students s
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));

		Join<Object, Object> join = courseRoot.join("students");

		List<Course> courses = query.getResultList();
		System.out.println("************************************************************************************************************************");
		logger.info("--------------------------------------------");
		logger.info("result -> {}", courses);
		logger.info("--------------------------------------------");
	}

	@Test
	public void criteria_left_join() {
		//	select c from Course c, Student s join c.students s
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root courseRoot = criteriaQuery.from(Course.class);
		TypedQuery query = entityManager.createQuery(criteriaQuery.select(courseRoot));

		Join join = courseRoot.join("students", JoinType.LEFT);

		List courses = query.getResultList();
		System.out.println("************************************************************************************************************************");
		logger.info("--------------------------------------------");
		logger.info("result -> {}", courses);
		logger.info("--------------------------------------------");
	}














}
