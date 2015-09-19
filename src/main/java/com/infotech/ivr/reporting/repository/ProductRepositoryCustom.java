package com.infotech.ivr.reporting.repository;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.domain.ProductReportFilter;
import com.infotech.ivr.reporting.domain.SortExpression;

import java.util.List;

/**
 *
 * custom repository for product domain object
 *
 */
public interface ProductRepositoryCustom {
    List<Product> findAll(int currentPage, int pageSize);
    List<Product> report(ProductReportFilter filter, int currentPage, int pageSize, List<SortExpression> sortExpressions);
    long reportCount(ProductReportFilter filter);
}
