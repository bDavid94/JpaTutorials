package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	@Test
	public void findById_basic() {
		Course course = courseRepository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}

	@Test
    @DirtiesContext
    public void deleteById_basic() {
	    courseRepository.deleteById(10002L);
	    Course deletedCourse = courseRepository.findById(10002L);
	    assertNull(deletedCourse);
    }

	@Test
	@DirtiesContext
	public void update() {
		Course course = courseRepository.findById(10002L);
		course.setName("Test");
		courseRepository.update(course);
		Course updatedCourse = courseRepository.findById(10002L);
		assertEquals("Test", updatedCourse.getName());
	}

	@Test
	@DirtiesContext
	public void save() {
		courseRepository.save(new Course("Test"));
	}

	@Test
	@DirtiesContext
	public void play() {
		courseRepository.playWithEntityManager();
	}

}
