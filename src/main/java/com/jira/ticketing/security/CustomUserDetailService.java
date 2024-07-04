package com.jira.ticketing.security;

import com.jira.ticketing.entity.Users;
import com.jira.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.getUserByUsername(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .build();
    }
}
