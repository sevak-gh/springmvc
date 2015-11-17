package com.infotech.ivr.reporting.repository;

import com.infotech.ivr.reporting.domain.Role;

import java.util.List;

/**
 *
 * repository for role domain object
 *
 */
public interface RoleRepository {
    List<Role> findAll();
    List<Role> findAllPageable(int currentPage, int pageSize);
    Role findById(long id);
    Role save(Role role);
    Long count();
}
