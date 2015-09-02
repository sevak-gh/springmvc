package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Product;
import com.infotech.ivr.reporting.repository.ProductRepository;

import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for Product repository.
 *
 */
public interface SpringDataJpaProductRepository extends ProductRepository, Repository<Product, Long> {
}
