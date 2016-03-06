package com.infotech.springmvc.service;

import com.infotech.springmvc.domain.Audit;

import java.util.List;

/**
 *
 * service for audit domain object.
 *
 */
public interface AuditService {
    List<Audit> findAll();
    List<Audit> findAllPageable(int currentPage, int pageSize);
    Audit findById(long id);
    Audit save(Audit audit);
    long getCount();
}
