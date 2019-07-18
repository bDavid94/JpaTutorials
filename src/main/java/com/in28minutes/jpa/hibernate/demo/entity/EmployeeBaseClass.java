package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "EMPLOYEE")
//@MappedSuperclass
public abstract class EmployeeBaseClass {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public EmployeeBaseClass() {
        // USED BY JPA
    }

    public EmployeeBaseClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployeeBaseClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
