package com.jira.ticketing.service;

import com.jira.ticketing.entity.User;
import com.jira.ticketing.entity.dto.UserRegistrationDto;
import com.jira.ticketing.entity.dto.UserUpdateDto;
import com.jira.ticketing.exception.UserNotFoundException;
import com.jira.ticketing.mapper.UserMapper;
import com.jira.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(UserRegistrationDto registrationDto) {

        User user = UserMapper.toUser(registrationDto);
        return userRepository.save(user);

    }

    public User getCurrentUser() {
        return null;
    }

    public User getUserById(Long id) {
//       Optional<User> userOptional = userRepository.findById(id);
//       if (userOptional.isEmpty()) {
//           throw new RuntimeException("User not found");
//       }
//       return userOptional.get();
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Long id, UserUpdateDto updateDto) {
        User user = getUserById(id);
        UserMapper.updateUser(user, updateDto);
        return userRepository.save(user);
    }
}
