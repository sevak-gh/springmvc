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
    List<Product> findAllPageable(int currentPage, int pageSize);
    Product findById(long id);
    Product save(Product product);
    Long count();
    List<Product> report(ProductReportFilter filter, List<SortExpression> sortExpressions);
    List<Product> reportPageable(ProductReportFilter filter, List<SortExpression> sortExpressions, int currentPage, int pageSize);
    long reportCount(ProductReportFilter filter);
}
