package com.jira.ticketing.service;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.ProjectDto;
import com.jira.ticketing.exception.ProjectNotFoundException;
import com.jira.ticketing.mapper.ProjectMapper;
import com.jira.ticketing.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public Project createProject(ProjectDto projectDto) {
        Project project = ProjectMapper.toProject(projectDto);
        Users users = userService.getUserById(projectDto.getUserId());
        project.setOwner(users);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Project updateProject(Long id, ProjectDto projectDto) {
        Project project = getProjectById(id);
        ProjectMapper.updateProject(project, projectDto);
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);

    }
}
