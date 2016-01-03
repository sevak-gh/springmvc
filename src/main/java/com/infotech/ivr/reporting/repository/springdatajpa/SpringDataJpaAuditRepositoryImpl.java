package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Audit;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;


/**
 *
 * custom implementation for Audit repository.
 *
 */
public class SpringDataJpaAuditRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<Audit> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT audit FROM Audit audit", Audit.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
