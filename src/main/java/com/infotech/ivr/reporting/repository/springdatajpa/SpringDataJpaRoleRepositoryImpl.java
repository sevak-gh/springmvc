package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Role;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;


/**
 *
 * custom implementation for Role repository.
 *
 */
public class SpringDataJpaRoleRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<Role> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT role from Role role LEFT JOIN FETCH role.permissions", Role.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
