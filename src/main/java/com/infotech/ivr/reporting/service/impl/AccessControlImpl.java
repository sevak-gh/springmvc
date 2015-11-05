package com.infotech.ivr.reporting.service.impl;

//import com.infotech.ivr.reporting.domain.User;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * access control implementation for user domain object.
 *
 */
@Service("AccessControlUserService")
public class AccessControlImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User("username", 
                        "$2a$10$OYn/llm.0MDbCI0l8XwQLuxEei4lUfA9HCNdX9h6dnaZ7gSwuI0eO", // password = password
                        authorities);
    }
}
