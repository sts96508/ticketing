package com.jira.ticketing.controller;

import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.UserUpdateDto;
import com.jira.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<Users> getCurrentUser() {
        Users users = userService.getCurrentUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users users = userService.getUserById(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto updateDto) {
        Users users = userService.updateUser(id, updateDto);
        return ResponseEntity.ok(users);
    }

}
