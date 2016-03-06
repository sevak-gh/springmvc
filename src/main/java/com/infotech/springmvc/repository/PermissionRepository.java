package com.infotech.springmvc.repository;

import com.infotech.springmvc.domain.Permission;

import java.util.List;

/**
 *
 * repository for permission domain object
 *
 */
public interface PermissionRepository {
    List<Permission> findAll();
    Permission findById(long id);
    Long count();
}
