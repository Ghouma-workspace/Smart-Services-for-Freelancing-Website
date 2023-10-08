package com.backengtest.demo.controller;

import com.backengtest.demo.dto.ProjectDto;
import com.backengtest.demo.service.ProjectServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(projectService.getAllProjects());
    }

    @PostMapping("/add" )
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.createProject(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id,@RequestBody ProjectDto projectDto) {
        ProjectDto updatedProjectDto = projectService.updateProject(id,projectDto);
        return ResponseEntity.ok().body(updatedProjectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectDto>> searchProjects(@RequestParam("title") String query) {
        List<ProjectDto> projectDtos = projectService.searchProjects(query);
        return ResponseEntity.ok().body(projectDtos);
    }
}
