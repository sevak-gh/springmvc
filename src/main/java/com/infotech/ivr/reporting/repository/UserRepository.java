package com.infotech.ivr.reporting.repository;

import com.infotech.ivr.reporting.domain.User;

import java.util.List;

/**
 *
 * repository for user domain object
 *
 */
public interface UserRepository {
    List<User> findAll();
    List<User> findAllPageable(int currentPage, int pageSize);
    User findById(long id);
    User findByUsername(String username);
    User save(User user);
    Long count();
}
