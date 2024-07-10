package com.jira.ticketing.mapper;

import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.UserRegistrationDto;
import com.jira.ticketing.entity.dto.UserUpdateDto;

public class UserMapper {
    public static Users toUser(UserRegistrationDto userRegistrationDto) {
        return Users.builder()
                .username(userRegistrationDto.getUsername())
                .password(userRegistrationDto.getPassword())
                .role(userRegistrationDto.getRole())
                .email(userRegistrationDto.getEmail())
                .build();
    }

    public static void updateUser(Users users, UserUpdateDto userUpdateDto) {
        if (userUpdateDto.getEmail() != null) {
            users.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getPassword() != null) {
            users.setPassword(userUpdateDto.getPassword());
        }
        if (userUpdateDto.getRole() != null) {
            users.setRole(userUpdateDto.getRole());
        }
    }
}
