package com.infotech.springmvc.repository;

import com.infotech.springmvc.domain.Audit;

import java.util.List;

/**
 *
 * repository for audit domain object
 *
 */
public interface AuditRepository {
    List<Audit> findAll();
    List<Audit> findAllPageable(int currentPage, int pageSize);
    Audit findById(long id);
    Audit save(Audit audit);
    Long count();
}
