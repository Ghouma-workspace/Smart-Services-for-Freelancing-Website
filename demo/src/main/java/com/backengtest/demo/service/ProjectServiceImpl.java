package com.backengtest.demo.service;

import com.backengtest.demo.dto.ProjectDto;
import com.backengtest.demo.exception.ProjectNotFoundException;
import com.backengtest.demo.mapper.ProjectMapper;
import com.backengtest.demo.model.Project;
import com.backengtest.demo.model.ProjectDomain;
import com.backengtest.demo.model.User;
import com.backengtest.demo.repository.ProjectDomainRepo;
import com.backengtest.demo.repository.ProjectRepo;
import com.backengtest.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;
    private final ProjectMapper projectMapper;
    private final ProjectDomainRepo projectDomainRepo;


    //matensech el projectApplication fil createProject !!!!!!!!!!!
    @Override
    @Transactional
    public ProjectDto createProject(ProjectDto projectDto) {
        //mel mafroudh tekhdem bel getCurrentUser()
        ProjectDomain projectDomain = projectDomainRepo.findById(projectDto.getProjectDomainId())
                .orElseThrow(() -> new IllegalArgumentException("Project domain not found"));
        Project save = projectRepo.save(projectMapper.mapProjectDtoToEntity(projectDto, projectDomain));
        projectDto.setId(save.getId());
        return projectDto;
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto project) {
        ProjectDomain projectDomain = projectDomainRepo.findById(project.getProjectDomainId())
                .orElseThrow(() -> new IllegalArgumentException("Project domain not found"));
        Project existingProject = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id "+ id + " not found"));
        existingProject.setProjectDomain(projectDomain);
        existingProject.setTitle(project.getTitle());
        existingProject.setDescription(project.getDescription());
        existingProject.setPayment(project.getPayment());
        existingProject.setActive(project.getActive());
        existingProject.setDate(project.getDate());
        return projectMapper.mapProjectToDto(projectRepo.save(existingProject));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id " + id + " not found"));
        return projectMapper.mapProjectToDto(project);
    }

    @Override
    @Transactional
    public List<ProjectDto> getAllProjects() {
        return   projectRepo.findAll()
                    .stream()
                   .map(projectMapper::mapProjectToDto)
                   .collect(toList());
    }

    public List<ProjectDto> searchProjects(String title) {
        List<Project> projects = projectRepo.findAllByTitleContaining(title);
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projects) {
            projectDtos.add(ProjectDto.fromProject(project));
        }
        return projectDtos;
    }

}

