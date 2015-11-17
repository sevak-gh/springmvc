package com.infotech.ivr.reporting.repository.springdatajpa;

import com.infotech.ivr.reporting.domain.User;
import com.infotech.ivr.reporting.repository.UserRepository;

import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * spring data jpa implementation for User repository.
 *
 */
public interface SpringDataJpaUserRepository extends UserRepository, Repository<User, Long> {

    @Override
    @Query(name="findByUsername")
    User findByUsername(@Param("username") String username);
    
    @Override
    @Query(name="findById")
    User findById(@Param("id") long id);
}
