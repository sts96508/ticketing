package com.jira.ticketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.ProjectDto;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

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
    void createProject() throws Exception {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("proja");
        projectDto.setDescription("some proj");
        projectDto.setUserId(user.getId());
        mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectDto))
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), "abcd"))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(projectDto.getName())))
                .andExpect(jsonPath("$.owner.email", is(user.getEmail())));

    }

    @Test
    void getAllProjects() throws Exception {
        createProject();

        mockMvc.perform(get("/api/projects")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), "abcd"))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("proja")));
    }

    @Test
    void getProjectById() throws Exception {
        createProject();
        mockMvc.perform(get("/api/projects/1")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getUsername(), "abcd"))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("proja")))
                .andExpect(jsonPath("$.owner.email", is(user.getEmail())));
        
    }
}