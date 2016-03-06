package com.infotech.springmvc.service;

import com.infotech.springmvc.domain.Permission;

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
