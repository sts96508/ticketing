package com.jira.ticketing.mapper;

import com.jira.ticketing.entity.User;
import com.jira.ticketing.entity.dto.UserRegistrationDto;
import com.jira.ticketing.entity.dto.UserUpdateDto;

public class UserMapper {
    public static User toUser(UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .username(userRegistrationDto.getUsername())
                .password(userRegistrationDto.getPassword())
                .role(userRegistrationDto.getRole())
                .email(userRegistrationDto.getEmail())
                .build();
    }
    public static void updateUser(User user, UserUpdateDto userUpdateDto) {
        if (userUpdateDto.getEmail()!=null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getPassword()!=null) {
            user.setPassword(userUpdateDto.getPassword());
        }
        if (userUpdateDto.getRole()!=null) {
            user.setRole(userUpdateDto.getRole());
        }

    }
}
