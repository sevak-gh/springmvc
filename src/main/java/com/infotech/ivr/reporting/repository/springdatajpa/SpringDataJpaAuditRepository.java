package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.Audit;
import com.infotech.ivr.reporting.repository.AuditRepository;

import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * spring data jpa implementation for Audit repository.
 *
 */
public interface SpringDataJpaAuditRepository extends AuditRepository, Repository<Audit, Long> {
}
