package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReviewRepository {

    @Autowired
    EntityManager entityManager;

    public Review findById(Long id) {
       return entityManager.find(Review.class, id);
    }

    public void save(Review review) {
        entityManager.persist(review);
    }

    public void update(Review review) {
        entityManager.merge(review);
    }

    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    public List<Review> findAll() {
        TypedQuery namedQuery = entityManager.createNamedQuery("get_all_reviews", Review.class);
        return namedQuery.getResultList();
    }
}
