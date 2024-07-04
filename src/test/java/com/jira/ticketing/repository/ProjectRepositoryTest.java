package com.jira.ticketing.repository;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.Users;
import com.jira.ticketing.utils.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;
    private Project savedProject;

    @BeforeEach
    public void setup() {
        Users users = new Users();
        users.setUsername(AppConstants.UsersConstants.USERNAME);
        users.setEmail(AppConstants.UsersConstants.EMAIL);
        users.setPassword(AppConstants.UsersConstants.PASSWORD);
        users.setRole(AppConstants.UsersConstants.ROLE);

        Project project = new Project();
        project.setName(AppConstants.PROJECTNAME);
        project.setDescription(AppConstants.PROJECTDESCRIPTION);
        project.setOwner(users);

        savedProject = projectRepository.save(project);

    }

    @Test
    public void testSave() {
        assertNotNull(savedProject);
    }

    @Test
    public void findById() {
        Long id = savedProject.getId();
        Optional<Project> optionalProject = projectRepository.findById(id);
        assertTrue(optionalProject.isPresent());
        Project project = optionalProject.get();
        assertEquals(AppConstants.PROJECTNAME, project.getName());
    }

    @Test
    public void findByIdNotFound() {
        Optional<Project> optionalProject = projectRepository.findById(333L);
        assertFalse(optionalProject.isPresent());
    }

    @Test
    public void deleteById() {
        Long id = savedProject.getId();
        projectRepository.deleteById(id);
        Optional<Project> optionalProject = projectRepository.findById(id);
        assertFalse(optionalProject.isPresent());

    }
}