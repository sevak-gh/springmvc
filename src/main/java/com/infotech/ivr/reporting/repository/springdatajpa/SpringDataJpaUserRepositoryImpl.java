package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.User;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;


/**
 *
 * custom implementation for User repository.
 *
 */
public class SpringDataJpaUserRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<User> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT user FROM User user LEFT JOIN FETCH user.roles", User.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
