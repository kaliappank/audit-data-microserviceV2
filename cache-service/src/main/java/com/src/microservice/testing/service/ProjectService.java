package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.model.Project;

public interface ProjectService {
    List<Project> getAllProjects();

    Project validateAndGetProject(Long id);

}	
