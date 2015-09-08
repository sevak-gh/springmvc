package com.infotech.ivr.reporting.repository;

import com.infotech.ivr.reporting.domain.Product;

import java.util.List;

/**
 *
 * repository for product domain object
 *
 */
public interface ProductRepository extends ProductRepositoryCustom {
    List<Product> findAll();
    Product findById(long id);
    Product save(Product product);
    Long count();
}
