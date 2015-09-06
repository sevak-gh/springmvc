package com.infotech.ivr.reporting.repository;

import com.infotech.ivr.reporting.domain.Product;

import java.util.List;

/**
 *
 * custom repository for product domain object
 *
 */
public interface ProductRepositoryCustom {
    List<Product> findAll(int currentPage, int pageSize);
}
