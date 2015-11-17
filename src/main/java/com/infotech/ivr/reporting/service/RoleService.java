package com.infotech.ivr.reporting.service;

import com.infotech.ivr.reporting.domain.Role;

import java.util.List;

/**
 *
 * service for role domain object.
 *
 */
public interface RoleService {
    List<Role> findAll();
    List<Role> findAllPageable(int currentPage, int pageSize);
    Role findById(long id);
    Role save(Role role);
    long getCount();
}
