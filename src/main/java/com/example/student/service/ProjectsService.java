package com.example.student.service;


import com.example.student.domain.Projects;
import com.example.student.repo.ProjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/projects")
public class ProjectsService {

    private final ProjectsRepo projectsRepo;

    @Autowired
    public ProjectsService(ProjectsRepo projectRepo) {
        this.projectsRepo = projectRepo;
    }

    public Projects createProject(Projects projects) {
        return projectsRepo.save(projects);
    }

    @GetMapping
    public Page<Projects> getAllProjects(int page, int size) {
        return projectsRepo.findAll(PageRequest.of(page, size));
    }

    public Projects getProject(String id) {
        return projectsRepo.findById(id).orElse(null);
    }
}


