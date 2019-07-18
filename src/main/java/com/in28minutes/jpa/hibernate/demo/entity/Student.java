package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "get_all_students", query = "select s from Student s")
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE",
        joinColumns = @JoinColumn(name = "STUDENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    private Set<Course> courses = new HashSet<>();

    public Student() {
        // USED BY JPA
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    public Student(String name) {
        this.name = name;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Passport getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\nStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
