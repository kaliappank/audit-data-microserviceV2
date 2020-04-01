package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.exception.ProjectNotFoundException;
import com.src.microservice.testing.model.Project;
import com.src.microservice.testing.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

    @Override
    public Project validateAndGetProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(String.format("Project with id %s not found", id)));
    }
}
