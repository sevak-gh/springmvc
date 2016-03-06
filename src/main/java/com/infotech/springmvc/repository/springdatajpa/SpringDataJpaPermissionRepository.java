package com.infotech.springmvc.repository.springdatajpa;

import com.infotech.springmvc.domain.Permission;
import com.infotech.springmvc.repository.PermissionRepository;

import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for Permission repository.
 *
 */
public interface SpringDataJpaPermissionRepository extends PermissionRepository, Repository<Permission, Long> {
}
