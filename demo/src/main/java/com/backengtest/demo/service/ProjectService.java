package com.backengtest.demo.service;

import com.backengtest.demo.dto.ProjectDto;
import com.backengtest.demo.model.Project;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto updateProject(Long id,ProjectDto projectDto);

    void deleteProject(Long id);

    ProjectDto getProjectById(Long id);

    List<ProjectDto> getAllProjects();

    List<ProjectDto> searchProjects(String title);
}
