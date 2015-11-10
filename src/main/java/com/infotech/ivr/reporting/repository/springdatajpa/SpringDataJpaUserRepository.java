package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.User;
import com.infotech.ivr.reporting.repository.UserRepository;

import org.springframework.data.repository.Repository;

/**
 *
 * spring data jpa implementation for User repository.
 *
 */
public interface SpringDataJpaUserRepository extends UserRepository, Repository<User, Long> {
}
