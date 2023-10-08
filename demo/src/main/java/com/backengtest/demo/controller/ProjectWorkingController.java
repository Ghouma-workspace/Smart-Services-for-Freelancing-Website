package com.backengtest.demo.controller;
import com.backengtest.demo.dto.ProjectApplicationDto;
import com.backengtest.demo.dto.ProjectDto;
import com.backengtest.demo.dto.ProjectWorkingDto;
import com.backengtest.demo.model.User;
import com.backengtest.demo.service.ProjectWorkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/work")
@Slf4j
public class ProjectWorkingController {
    private final ProjectWorkingService projectService;

    public ProjectWorkingController(ProjectWorkingService projectService) {
        this.projectService = projectService;
    }

    /*
    @PostMapping("/add" )
    public ResponseEntity<ProjectWorkingDto> createProject(@RequestBody ProjectWorkingDto projectDto, @RequestBody User user) {
        Project
        ProjectWorkingDto createdProject = projectService.createProject(projectDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<ProjectWorkingDto> getProject(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectWorkingDto>> getAllProjects() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getAllProjects());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectWorkingDto> updateProject(@PathVariable Long id,@RequestBody ProjectWorkingDto projectDto) {
        ProjectWorkingDto updatedProjectDto = projectService.updateProject(id,projectDto);
        return ResponseEntity.ok().body(updatedProjectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ProjectWorkingDto>> getAllProjectsByUsername(@PathVariable String username) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getAllProjectsByUsername(username));
    }
}
