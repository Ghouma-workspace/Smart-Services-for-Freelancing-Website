package com.backengtest.demo.service;

import com.backengtest.demo.dto.ProjectDto;
import com.backengtest.demo.exception.ProjectNotFoundException;
import com.backengtest.demo.model.Project;
import com.backengtest.demo.model.ProjectRec;
import com.backengtest.demo.repository.ProjectRecRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectRecService {

    private final ProjectRecRepository projectRepo;

    @Transactional
    public ProjectRec getProjectById(Long id) {
        ProjectRec project = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id " + id + " not found"));
        return project;
    }

    @Transactional
    public List<ProjectRec> getAllProjects() {
        return   projectRepo.findAll();
    }

    @Transactional
    public ProjectRec searchProjects(String title) {
        List<ProjectRec> projects = projectRepo.findByTitle(title);
        return projects.get(0);
    }
}
