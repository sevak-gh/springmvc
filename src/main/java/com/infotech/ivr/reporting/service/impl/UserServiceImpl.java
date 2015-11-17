package com.infotech.ivr.reporting.service.impl;

import com.infotech.ivr.reporting.domain.User;
import com.infotech.ivr.reporting.domain.Role;
import com.infotech.ivr.reporting.domain.Permission;
import com.infotech.ivr.reporting.service.UserService;
import com.infotech.ivr.reporting.repository.UserRepository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * service implementation for user domain object, including access control.
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username[" + username + "] not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()){
            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                      user.getPassword(),
                                                                      authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> findAllPageable(int currentPage, int pageSize) {
        return userRepository.findAllPageable(currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User save(User user) {
        // hash the password
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return userRepository.count();
    }
}
