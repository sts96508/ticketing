package com.jira.ticketing.repository;

import com.jira.ticketing.entity.Users;
import com.jira.ticketing.utils.AppConstants;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application.properties")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private Users savedUser1;
    private Users savedUser2;

    @BeforeEach
    void setUp() {
        Users user1 = new Users();
        user1.setUsername(AppConstants.UsersConstants.USERNAME);
        user1.setPassword(AppConstants.UsersConstants.PASSWORD);
        user1.setEmail(AppConstants.UsersConstants.EMAIL);
        user1.setRole(AppConstants.UsersConstants.ROLE);

        Users user2 = new Users();
        user2.setUsername(AppConstants.UsersConstants.USERNAME1);
        user2.setPassword(AppConstants.UsersConstants.PASSWORD1);
        user2.setEmail(AppConstants.UsersConstants.EMAIL1);
        user2.setRole(AppConstants.UsersConstants.ROLE1);

        savedUser1 = userRepository.save(user1);
        savedUser2 = userRepository.save(user2);
    }

    @Test
    void testSave() {
        assertNotNull(savedUser1);
        assertNotNull(savedUser2);
    }

    @Test
    void findById() {
        Long id = savedUser1.getId();
        Optional<Users> optionalUser = userRepository.findById(id);
        assertTrue(optionalUser.isPresent());
    }

    @Test
    void findByIdNotFound() {
        Optional<Users> optionalUser = userRepository.findById(999L);
        assertFalse(optionalUser.isPresent());
    }

    @Test
    void findByUsername() {
        Optional<Users> optionalUser = userRepository.findByUsername(savedUser2.getUsername());
        assertTrue(optionalUser.isPresent());
        Users user = optionalUser.get();
        assertEquals(savedUser2.getId(), user.getId());
        assertEquals(savedUser2.getEmail(), user.getEmail());
    }

    @Test
    void findByUsernameNotFound() {
        Optional<Users> optionalUser = userRepository.findByUsername("dummy");
        assertFalse(optionalUser.isPresent());
    }
}