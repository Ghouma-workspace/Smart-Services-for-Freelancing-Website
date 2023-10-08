package com.backengtest.demo.controller;


import com.backengtest.demo.dto.ProjectDto;
import com.backengtest.demo.model.ProjectRec;
import com.backengtest.demo.service.ProjectRecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/projectsrec")
@Slf4j
public class ProjectRecController {
    private final ProjectRecService projectService;

    public ProjectRecController(ProjectRecService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectRec> getProject(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectRec>> getAllProjects() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getAllProjects());
    }

    @GetMapping("/search")
    public ResponseEntity<ProjectRec> searchProjects(@RequestParam("title") String query) {
        ProjectRec project = projectService.searchProjects(query);
        return ResponseEntity.ok().body(project);
    }
}
