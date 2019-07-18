package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public void save(Student student) {
        entityManager.persist(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("M55533");
        Student student = new Student("Mike");
        student.setPassport(passport);
        entityManager.persist(passport);
        entityManager.persist(student);
    }

    public void insertStudentAndCourse(){
        Student student = new Student("Chad");
        Course course = new Course("Microservices in 100 Steps");
        entityManager.persist(student);
        entityManager.persist(course);
        student.addCourse(course);
//        course.addStudent(student);
//        entityManager.persist(student);

    }

    public void update(Student student) {
        entityManager.merge(student);
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    public List<Student> findAll() {
        TypedQuery<Student> namedQuery = entityManager.createNamedQuery("get_all_students", Student.class);
        return namedQuery.getResultList();
    }
}
