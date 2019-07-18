package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PassportRepository {

    @Autowired
    EntityManager entityManager;

    public Passport findById(Long id) {
        return entityManager.find(Passport.class, id);
    }

    public void save(Passport passport) {
        entityManager.persist(passport);
    }

    public void update(Passport passport) {
        entityManager.merge(passport);
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    public List<Passport> findAll() {
        TypedQuery<Passport> namedQuery = entityManager.createNamedQuery("get_all_passports", Passport.class);
        return namedQuery.getResultList();
    }
}
