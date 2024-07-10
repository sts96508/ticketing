package com.jira.ticketing.service;

import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.UserRegistrationDto;
import com.jira.ticketing.entity.dto.UserUpdateDto;
import com.jira.ticketing.exception.UserNotFoundException;
import com.jira.ticketing.mapper.UserMapper;
import com.jira.ticketing.repository.UserRepository;
import com.jira.ticketing.utils.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users register(UserRegistrationDto registrationDto) {

        Users users = UserMapper.toUser(registrationDto);
        users.setPassword(BCryptUtils.encodePassword(users.getPassword()));
        return userRepository.save(users);
    }

    public Users getCurrentUser() {
        return null;
    }

    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public Users getUserById(Long id) {
//       Optional<User> userOptional = userRepository.findById(id);
//       if (userOptional.isEmpty()) {
//           throw new RuntimeException("User not found");
//       }
//       return userOptional.get();

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Users updateUser(Long id, UserUpdateDto updateDto) {
        Users users = getUserById(id);
        UserMapper.updateUser(users, updateDto);
        return userRepository.save(users);
    }
}
