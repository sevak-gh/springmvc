package com.infotech.ivr.reporting.repository;

import com.infotech.ivr.reporting.domain.Permission;

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
