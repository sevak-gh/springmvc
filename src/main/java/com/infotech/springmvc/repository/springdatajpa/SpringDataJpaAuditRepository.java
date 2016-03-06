package com.infotech.springmvc.repository.springdatajpa;

import com.infotech.springmvc.domain.Audit;
import com.infotech.springmvc.repository.AuditRepository;

import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for Audit repository.
 *
 */
public interface SpringDataJpaAuditRepository extends AuditRepository, Repository<Audit, Long> {
}
