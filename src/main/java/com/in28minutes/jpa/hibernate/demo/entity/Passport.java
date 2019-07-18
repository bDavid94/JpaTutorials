package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "get_all_passports", query = "select p from Passport p")
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    public Passport() {
        // USED BY JPA
    }

    public Passport(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "\nPassport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
