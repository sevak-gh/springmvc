package com.infotech.ivr.reporting.service;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.domain.SortExpression;
import com.infotech.ivr.reporting.domain.ProductReportFilter;

import java.util.List;

/**
 *
 * service for product domain object.
 *
 */
public interface ProductService {
    List<Product> findAll();
    List<Product> findAllPageable(int currentPage, int pageSize);
    Product findById(long id);
    Product save(Product product);
    long getCount();
    List<Product> report(ProductReportFilter filter, List<SortExpression> sortExpressions);
    List<Product> report(ProductReportFilter filter, String sortField, boolean isAsc);
    List<Product> reportPageable(ProductReportFilter filter, List<SortExpression> sortExpressions, int currentPage, int pageSize);
    List<Product> reportPageable(ProductReportFilter filter, String sortField, boolean isAsc, int currentPage, int pageSize);
    long reportCount(ProductReportFilter filter);
}
