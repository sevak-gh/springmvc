package com.infotech.springmvc.repository.springdatajpa;

import com.infotech.springmvc.domain.User;
import com.infotech.springmvc.repository.UserRepository;

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
    @Query("SELECT user FROM User user LEFT JOIN FETCH user.roles r LEFT JOIN FETCH r.permissions p WHERE user.username = :username")
    User findByUsername(@Param("username") String username);
    
    @Override
    @Query("SELECT user FROM User user LEFT JOIN FETCH user.roles WHERE user.id = :id")
    User findById(@Param("id") long id);
}
