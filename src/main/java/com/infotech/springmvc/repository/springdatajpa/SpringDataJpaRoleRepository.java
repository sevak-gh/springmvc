package com.infotech.springmvc.repository.springdatajpa;

import com.infotech.springmvc.domain.Role;
import com.infotech.springmvc.repository.RoleRepository;

import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * spring data jpa implementation for Role repository.
 *
 */
public interface SpringDataJpaRoleRepository extends RoleRepository, Repository<Role, Long> {

    @Override
    @Query("SELECT role FROM Role role LEFT JOIN FETCH role.permissions WHERE role.id = :id")
    Role findById(@Param("id") long id);
}
