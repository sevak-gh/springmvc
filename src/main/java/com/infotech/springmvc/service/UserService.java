package com.infotech.springmvc.service;

import com.infotech.springmvc.domain.User;

import java.util.List;

/**
 *
 * service for user domain object.
 *
 */
public interface UserService {
    List<User> findAll();
    List<User> findAllPageable(int currentPage, int pageSize);
    User findById(long id);
    User findByUsername(String username);
    User save(User user);
    long getCount();
}
