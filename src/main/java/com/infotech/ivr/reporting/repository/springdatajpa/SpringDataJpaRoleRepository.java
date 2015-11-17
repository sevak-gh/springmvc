package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Role;
import com.infotech.ivr.reporting.repository.RoleRepository;

import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for Role repository.
 *
 */
public interface SpringDataJpaRoleRepository extends RoleRepository, Repository<Role, Long> {
}
