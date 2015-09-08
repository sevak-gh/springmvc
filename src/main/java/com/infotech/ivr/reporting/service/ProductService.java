package com.infotech.ivr.reporting.service;

import com.infotech.ivr.reporting.domain.Product;

import java.util.List;

/**
 *
 * service for product domain object.
 *
 */
public interface ProductService {
    List<Product> findAll();
    List<Product> findAll(int currentPage, int pageSize);
    Product findById(long id);
    Product save(Product product);
    long getCount();
}
