package com.infotech.springmvc.service.impl;

import com.infotech.springmvc.domain.Audit;
import com.infotech.springmvc.service.AuditService;
import com.infotech.springmvc.repository.AuditRepository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
/**
 *
 * service implementation for audit domain object.
 * transaction is set to requires_new so that running
 * as aspect will not affect the ongoing transaction. 
 */
@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Audit> findAll() {
        return auditRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Audit> findAllPageable(int currentPage, int pageSize) {
        return auditRepository.findAllPageable(currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Audit findById(long id) {
        return auditRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Audit save(Audit audit) {
        return auditRepository.save(audit);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public long getCount() {
        return auditRepository.count();
    }
}
