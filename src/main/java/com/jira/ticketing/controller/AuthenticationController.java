package com.jira.ticketing.controller;

import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.JwtResponse;
import com.jira.ticketing.entity.dto.LoginRequest;
import com.jira.ticketing.entity.dto.UserRegistrationDto;
import com.jira.ticketing.service.AuthService;
import com.jira.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody UserRegistrationDto registrationDto) {
        Users users = userService.register(registrationDto);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {

        JwtResponse jwtResponse = authService.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

}
