package com.example.student.controller;

import com.example.student.domain.Projects;
import com.example.student.service.ProjectsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectsService projectsService;

    public ProjectController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @PostMapping
    public ResponseEntity<Projects> createProject(@RequestBody Projects project) {
        Projects createdProject = projectsService.createProject(project);
        return ResponseEntity.created(URI.create("/projects/" + createdProject.getId())).body(createdProject);
    }

    @GetMapping
    public ResponseEntity<Page<Projects>> getProjects(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Projects> projects = projectsService.getAllProjects(page, size);
        return ResponseEntity.ok().body(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projects> getProject(@PathVariable String id) {
        Projects project = projectsService.getProject(id);
        if (project != null) {
            return ResponseEntity.ok().body(project);
        }
        return ResponseEntity.notFound().build();
    }
}

