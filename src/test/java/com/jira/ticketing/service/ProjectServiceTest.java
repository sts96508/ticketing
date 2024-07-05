package com.jira.ticketing.service;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.ProjectDto;
import com.jira.ticketing.mapper.ProjectMapper;
import com.jira.ticketing.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createProject() {

        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("proj");
        projectDto.setDescription("some prj");
        projectDto.setUserId(33L);
        Project project = ProjectMapper.toProject(projectDto);
        Users user = new Users();
        user.setId(33L);
        user.setEmail("ddd@dd");
        when(userService.getUserById(anyLong())).thenReturn(user);
        when(projectRepository.save(any(Project.class))).thenReturn(project);
        Project result = projectService.createProject(projectDto);
        assertNotNull(result);
        assertEquals(project.getName(), result.getName());
        verify(userService).getUserById(eq(projectDto.getUserId()));
        verify(projectRepository).save(any(Project.class));


//        Project project = ProjectMapper.toProject(projectDto);
//        Users users = userService.getUserById(projectDto.getUserId());
//        project.setOwner(users);
//        return projectRepository.save(project);

    }

    @Test
    void getAllProjects() {
    }

    @Test
    void getProjectById() {
    }

    @Test
    void updateProject() {
    }

    @Test
    void deleteProject() {
    }
}