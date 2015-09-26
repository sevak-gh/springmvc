package com.infotech.ivr.reporting.repository;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.domain.ProductReportFilter;
import com.infotech.ivr.reporting.domain.SortExpression;

import java.util.List;

/**
 *
 * repository for product domain object
 *
 */
public interface ProductRepository {
    List<Product> findAll();
    Product findById(long id);
    Product save(Product product);
    Long count();
    List<Product> findAll(int currentPage, int pageSize);
    List<Product> report(ProductReportFilter filter, int currentPage, int pageSize, List<SortExpression> sortExpressions);
    long reportCount(ProductReportFilter filter);
}
