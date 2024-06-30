package com.jira.ticketing.mapper;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.dto.ProjectDto;

public class ProjectMapper {
    public static Project toProject(ProjectDto projectDto) {
        return Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .build();
    }

    public static void updateProject(Project project, ProjectDto projectDto) {
        if (projectDto.getName() != null) {
            project.setName(projectDto.getName());
        }
        if (projectDto.getDescription() != null) {
            project.setDescription(projectDto.getDescription());
        }

    }
}
