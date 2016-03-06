package com.infotech.springmvc.service.impl;

import com.infotech.springmvc.domain.Product;
import com.infotech.springmvc.domain.SortExpression;
import com.infotech.springmvc.domain.ProductReportFilter;
import com.infotech.springmvc.service.ProductService;
import com.infotech.springmvc.repository.ProductRepository;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * service implementation for product domain object.
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    //@PreAuthorize("hasAnyAuthority('product_list_view')")
    public List<Product> findAllPageable(int currentPage, int pageSize) {
        return productRepository.findAllPageable(currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    //@PreAuthorize("hasAnyAuthority('product_create_do','product_update_do')")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return productRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> reportPageable(ProductReportFilter filter, List<SortExpression> sortExpressions, int currentPage, int pageSize) {
        return productRepository.reportPageable(filter, sortExpressions, currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> reportPageable(ProductReportFilter filter, String sortField, boolean isAsc, int currentPage, int pageSize) {
        SortExpression sortExpression = new SortExpression();
        sortExpression.setField(sortField);
        sortExpression.setDirection((isAsc) ? SortExpression.Direction.ASC : SortExpression.Direction.DESC);
        List<SortExpression> sortExpressions = new ArrayList<SortExpression>();
        sortExpressions.add(sortExpression);
        return productRepository.reportPageable(filter, sortExpressions, currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> report(ProductReportFilter filter, List<SortExpression> sortExpressions) {
        return productRepository.report(filter, sortExpressions);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> report(ProductReportFilter filter, String sortField, boolean isAsc) {
        SortExpression sortExpression = new SortExpression();
        sortExpression.setField(sortField);
        sortExpression.setDirection((isAsc) ? SortExpression.Direction.ASC : SortExpression.Direction.DESC);
        List<SortExpression> sortExpressions = new ArrayList<SortExpression>();
        sortExpressions.add(sortExpression);
        return productRepository.report(filter, sortExpressions);
    }

    @Override
    @Transactional(readOnly = true)
    public long reportCount(ProductReportFilter filter) {
        return productRepository.reportCount(filter);
    }
}
