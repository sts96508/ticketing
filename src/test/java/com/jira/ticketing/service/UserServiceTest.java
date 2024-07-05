package com.jira.ticketing.service;

import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.UserRegistrationDto;
import com.jira.ticketing.exception.UserNotFoundException;
import com.jira.ticketing.mapper.UserMapper;
import com.jira.ticketing.repository.UserRepository;
import com.jira.ticketing.utils.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        //  MockitoAnnotations.openMocks(this);

    }

    @Test
    void register() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername(AppConstants.UsersConstants.USERNAME);
        userRegistrationDto.setEmail(AppConstants.UsersConstants.EMAIL);
        userRegistrationDto.setPassword(AppConstants.UsersConstants.PASSWORD);
        userRegistrationDto.setRole(AppConstants.UsersConstants.ROLE);
        Users user = UserMapper.toUser(userRegistrationDto);
        when(userRepository.save(any(Users.class))).thenReturn(user);
        Users result = userService.register(userRegistrationDto);
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void getCurrentUser() {
    }

    @Test
    void getUserByUsername() {
    }

    @Test
    void getUserById() {
        Users user = new Users();
        user.setId(22L);
        user.setEmail("ss@dd");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Users result = userService.getUserById(22L);
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        verify(userRepository).findById(eq(22L));
    }

    @Test
    void getUserByIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> userService.getUserById(44L));
        assertEquals("user with id " + 44 + " not found", userNotFoundException.getMessage());
    }

    @Test
    void updateUser() {
    }
}