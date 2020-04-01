package com.src.microservice.testing.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.src.microservice.testing.dto.InterfaceDto;
import com.src.microservice.testing.model.Interface;
import com.src.microservice.testing.service.InterfaceService;
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
@RequestMapping("api/interfaces")
public class InterfaceController {
	
	private final InterfaceService interfaceService;
	private final MapperFacade mapperFacade;

	public InterfaceController(InterfaceService interfaceService, MapperFacade mapperFacade) {
		this.interfaceService = interfaceService;
		this.mapperFacade = mapperFacade;
	}

	@GetMapping
	public List<InterfaceDto> getAllInterfaces(){
		return interfaceService.getAllInterfaces().stream().map(interface1 -> mapperFacade.map(interface1, InterfaceDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public InterfaceDto getInterface(@PathVariable Long id) {
		Interface interface2 = interfaceService.validateAndGetInterface(id);
		return mapperFacade.map(interface2, InterfaceDto.class);
	}	

}