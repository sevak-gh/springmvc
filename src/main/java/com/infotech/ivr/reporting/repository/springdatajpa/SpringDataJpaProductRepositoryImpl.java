package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.repository.ProductRepositoryCustom;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

/**
 *
 * custom implementation for Product repository.
 *
 */
public class SpringDataJpaProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    @Override
    public List<Product> findAll(int currentPage, int pageSize) {
        return em.createQuery("SELECT product from Product product", Product.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
