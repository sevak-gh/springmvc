package com.infotech.ivr.reporting.service.impl;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.domain.ProductReportFilter;
import com.infotech.ivr.reporting.service.ProductService;
import com.infotech.ivr.reporting.repository.ProductRepository;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Product> findAll(int currentPage, int pageSize) {
        //return productRepository.findAll(currentPage, pageSize);
        ProductReportFilter filter = new ProductReportFilter();
        //filter.setPrice(BigDecimal.valueOf(57));
        //filter.setName("کت");
        filter.setFromDate(LocalDateTime.of(2015, 9, 1, 1, 0, 0));
        return productRepository.report(filter, currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return productRepository.count();
    }
}
