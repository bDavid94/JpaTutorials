package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.EmployeeBaseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    EntityManager entityManager;

    public void add(EmployeeBaseClass employee) {
        entityManager.persist(employee);
    }

    public List<EmployeeBaseClass> findAll() {
        return entityManager.createQuery("select e from EmployeeBaseClass e", EmployeeBaseClass.class)
                .getResultList();
    }
}
