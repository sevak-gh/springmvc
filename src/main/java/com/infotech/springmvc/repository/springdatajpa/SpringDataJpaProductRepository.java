package com.infotech.springmvc.repository.springdatajpa;

import com.infotech.springmvc.domain.Product;
import com.infotech.springmvc.repository.ProductRepository;

import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for Product repository.
 *
 */
public interface SpringDataJpaProductRepository extends ProductRepository, Repository<Product, Long> {
}
