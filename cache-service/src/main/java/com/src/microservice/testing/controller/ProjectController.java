package com.src.microservice.testing.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.src.microservice.testing.dto.ProjectDto;
import com.src.microservice.testing.model.Project;
import com.src.microservice.testing.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.glasnost.orika.MapperFacade;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
	
	private final ProjectService projectService;
	private final MapperFacade mapperFacade;

	public ProjectController(ProjectService projectService, MapperFacade mapperFacade) {
		this.projectService = projectService;
		this.mapperFacade = mapperFacade;
	}

	@GetMapping
	public List<ProjectDto> getAllProjects(){
		return projectService.getAllProjects().stream().map(project -> mapperFacade.map(project, ProjectDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ProjectDto getProject(@PathVariable Long id) {
		Project project = projectService.validateAndGetProject(id);
		return mapperFacade.map(project, ProjectDto.class);
	}	

}