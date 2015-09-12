package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.domain.ProductReportFilter;
import com.infotech.ivr.reporting.repository.ProductRepositoryCustom;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Path;


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

    /**
     * get product report 
     */
    @Override
    public List<Product> report(ProductReportFilter filter, int currentPage, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if ((filter != null)&& (filter.getPrice() != null)) {
            predicates.add(builder.equal(product.get("price"), filter.getPrice()));
        }
        if ((filter != null)&& (filter.getName() != null)) {
            predicates.add(builder.like(product.get("name"), "%" + filter.getName() + "%"));
        }
        if ((filter != null)&& (filter.getFromDate() != null)) {
            predicates.add(builder.greaterThanOrEqualTo(product.<LocalDateTime>get("dateTime"), filter.getFromDate()));
        }
        if ((filter != null)&& (filter.getToDate() != null)) {
            predicates.add(builder.lessThanOrEqualTo(product.<LocalDateTime>get("dateTime"), filter.getToDate()));
        }
        criteria.where(builder.and(predicates.toArray(new Predicate[0])));
        return em.createQuery(criteria)
                 .setFirstResult((currentPage-1) * pageSize)
                 .setMaxResults(pageSize)
                 .getResultList();
    }

    /**
     * get product report count 
     */
    @Override
    public long reportCount(ProductReportFilter filter) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Product> product = criteria.from(Product.class);
        criteria.select(builder.count(product));
        List<Predicate> predicates = new ArrayList<Predicate>();
        if ((filter != null)&& (filter.getPrice() != null)) {
            predicates.add(builder.equal(product.get("price"), filter.getPrice()));
        }
        if ((filter != null)&& (filter.getName() != null)) {
            predicates.add(builder.like(product.get("name"), "%" + filter.getName() + "%"));
        }
        if ((filter != null)&& (filter.getFromDate() != null)) {
            predicates.add(builder.greaterThanOrEqualTo(product.<LocalDateTime>get("dateTime"), filter.getFromDate()));
        }
        if ((filter != null)&& (filter.getToDate() != null)) {
            predicates.add(builder.lessThanOrEqualTo(product.<LocalDateTime>get("dateTime"), filter.getToDate()));
        }
        criteria.where(builder.and(predicates.toArray(new Predicate[0])));
        return em.createQuery(criteria).getSingleResult();
    }
}
