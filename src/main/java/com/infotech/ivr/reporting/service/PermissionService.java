package com.infotech.ivr.reporting.service;

import com.infotech.ivr.reporting.domain.Permission;

import java.util.List;

/**
 *
 * service for permission domain object.
 *
 */
public interface PermissionService {
    List<Permission> findAll();
    Permission findById(long id);
    long getCount();
}
