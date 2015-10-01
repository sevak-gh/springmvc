package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.domain.SortExpression;
import com.infotech.ivr.reporting.domain.ProductReportFilter;

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
import javax.persistence.criteria.Order;


/**
 *
 * custom implementation for Product repository.
 *
 */
public class SpringDataJpaProductRepositoryImpl {

    @PersistenceContext
    EntityManager em;

    /**
     * get paged list 
     * currentPage numbered from 1
     *
     */
    public List<Product> findAllPageable(int currentPage, int pageSize) {
        return em.createQuery("SELECT product from Product product", Product.class)
                    .setFirstResult((currentPage-1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
    }

    /**
     * get product report 
     */
    public List<Product> reportPageable(ProductReportFilter filter, List<SortExpression> sortExpressions, int currentPage, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        prepareCriteriaPredicates(builder, criteria, product, filter);
        prepareCriteriaOrder(builder, criteria, product, sortExpressions);        
        return em.createQuery(criteria)
                 .setFirstResult((currentPage-1) * pageSize)
                 .setMaxResults(pageSize)
                 .getResultList();
    }

    /**
     * get product report 
     */
    public List<Product> report(ProductReportFilter filter, List<SortExpression> sortExpressions) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);
        prepareCriteriaPredicates(builder, criteria, product, filter);
        prepareCriteriaOrder(builder, criteria, product, sortExpressions);        
        return em.createQuery(criteria)
                 .getResultList();
    }

    /**
     * get product report count 
     */
    public long reportCount(ProductReportFilter filter) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Product> product = criteria.from(Product.class);
        criteria.select(builder.count(product));
        prepareCriteriaPredicates(builder, criteria, product, filter);
        return em.createQuery(criteria).getSingleResult();
    }

    /**
     * prepares predicate for criteria.
     */
    private void prepareCriteriaPredicates(CriteriaBuilder builder, CriteriaQuery<?> criteria, Root<Product> product, ProductReportFilter filter) {
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
    }
 
    /**
     * prepares orderBy for criteria.
     */
    private void prepareCriteriaOrder(CriteriaBuilder builder, CriteriaQuery<?> criteria, Root<Product> product, List<SortExpression> sortExpressions) {
        if (sortExpressions != null) {
            List<Order> orderList = new ArrayList<Order>();
            for (SortExpression sortExpression : sortExpressions) {
                if (sortExpression.getDirection() == SortExpression.Direction.ASC) {
                    orderList.add(builder.asc(product.get(sortExpression.getField())));
                } else {
                    orderList.add(builder.desc(product.get(sortExpression.getField())));
                }
            }
            criteria.orderBy(orderList.toArray(new Order[0]));
        }
    }
}
