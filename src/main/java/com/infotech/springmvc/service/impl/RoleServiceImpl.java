package com.infotech.springmvc.service.impl;

import com.infotech.springmvc.domain.Role;
import com.infotech.springmvc.service.RoleService;
import com.infotech.springmvc.repository.RoleRepository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * service implementation for role domain object.
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllPageable(int currentPage, int pageSize) {
        return roleRepository.findAllPageable(currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(long id) {
        return roleRepository.findById(id);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return roleRepository.count();
    }
}
