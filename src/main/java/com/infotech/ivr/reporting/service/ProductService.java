package com.infotech.ivr.reporting.service;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.domain.ProductReportFilter;

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
    List<Product> report(ProductReportFilter filter, int currentPage, int pageSize);
    long reportCount(ProductReportFilter filter);
}
