package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public void save(Course course) {
        entityManager.persist(course);
    }

    public void update(Course course) { entityManager.merge(course); }

    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    public void playWithEntityManager() {
        Course course = new Course("Web Services in 100 Steps");
        entityManager.persist(course);
        course.setName("Web Services in 100 Steps - Updated");
    }

    public List<Course> findAll() {
        TypedQuery<Course> namedQuery = entityManager
                .createNamedQuery("query_get_all_courses", Course.class);
        return namedQuery.getResultList();
    }


    public void addReviewForCourse(Long courseId, Review review) {
        Course course = findById(courseId);
        course.addReview(review);
        review.setCourse(course);
        entityManager.persist(review);
    }
}
