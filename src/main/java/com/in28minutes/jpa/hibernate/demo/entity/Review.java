package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "get_all_reviews", query = "select r from Review r")
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Integer rating;

    @ManyToOne
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Review() {
        // USED BY JPA
    }

    public Review(String description, Integer rating) {
        this.description = description;
        this.rating = rating;
    }

    public Review(Integer rating) {
        this.rating = rating;
    }
}
