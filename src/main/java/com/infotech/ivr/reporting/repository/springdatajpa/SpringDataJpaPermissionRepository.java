package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Permission;
import com.infotech.ivr.reporting.repository.PermissionRepository;

import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for Permission repository.
 *
 */
public interface SpringDataJpaPermissionRepository extends PermissionRepository, Repository<Permission, Long> {
}
