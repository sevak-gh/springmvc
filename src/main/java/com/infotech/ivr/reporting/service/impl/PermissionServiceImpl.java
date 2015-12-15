package com.infotech.ivr.reporting.service.impl;

import com.infotech.ivr.reporting.domain.Permission;
import com.infotech.ivr.reporting.service.PermissionService;
import com.infotech.ivr.reporting.repository.PermissionRepository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * service implementation for permission domain object.
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Permission findById(long id) {
        return permissionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return permissionRepository.count();
    }
}
