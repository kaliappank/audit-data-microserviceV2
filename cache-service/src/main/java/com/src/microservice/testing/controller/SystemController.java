package com.src.microservice.testing.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.src.microservice.testing.dto.SystemDto;
import com.src.microservice.testing.model.System;
import com.src.microservice.testing.service.SystemService;
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
@RequestMapping("api/systems")
public class SystemController {
	
	private final SystemService systemService;
	private final MapperFacade mapperFacade;

	public SystemController(SystemService systemService, MapperFacade mapperFacade) {
		this.systemService = systemService;
		this.mapperFacade = mapperFacade;
	}

	@GetMapping
	public List<SystemDto> getAllSystems(){
		return systemService.getAllSystems().stream().map(system -> mapperFacade.map(system, SystemDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public SystemDto getSystem(@PathVariable Long id) {
		System system = systemService.validateAndGetSystem(id);
		return mapperFacade.map(system, SystemDto.class);
	}	

}