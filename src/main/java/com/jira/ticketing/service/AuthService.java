package com.jira.ticketing.service;

import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.JwtResponse;
import com.jira.ticketing.entity.dto.LoginRequest;
import com.jira.ticketing.utils.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public JwtResponse login(LoginRequest loginRequest) {
        //validate user
        //return jwt token
        Users user = userService.getUserByUsername(loginRequest.getUsername());
        if (!BCryptUtils.matchPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("invalid credentials");
        }
        return new JwtResponse(jwtService.generateToken(loginRequest.getUsername()));
    }
}
