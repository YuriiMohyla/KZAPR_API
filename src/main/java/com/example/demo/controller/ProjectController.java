package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/project/{id}")
    public Optional<Project> getProjectById(@PathVariable(value = "id") Long project_id) {
        return projectRepository.findById(project_id);
    }

    
}
