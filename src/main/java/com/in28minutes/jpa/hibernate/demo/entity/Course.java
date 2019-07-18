package com.in28minutes.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@NamedQuery(name = "query_get_all_courses", query = "select c from Course c")
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "course")
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public Course(String name) {
        this.name = name;
    }

    protected Course() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
