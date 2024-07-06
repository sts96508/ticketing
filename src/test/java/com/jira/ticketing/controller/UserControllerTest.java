package com.jira.ticketing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.UserRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Users user;

    @BeforeEach
    void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername("abc");
        userRegistrationDto.setPassword("abcd");
        userRegistrationDto.setEmail("dd@dd");
        userRegistrationDto.setRole("USER");
        MvcResult mvcResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegistrationDto))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is(userRegistrationDto.getUsername())))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        user = objectMapper.readValue(response, Users.class);

    }

    @Test
    void getCurrentUser() {
    }

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/api/users/" + user.getId())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), "abcd"))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    void getUserByIdWithoutAuth() throws Exception {
        mockMvc.perform(get("/api/users/" + user.getId())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void updateUser() {
    }
}