package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.exception.SystemNotFoundException;
import com.src.microservice.testing.model.System;
import com.src.microservice.testing.repository.SystemRepository;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {

	private final SystemRepository systemRepository;

	public SystemServiceImpl(SystemRepository systemRepository) {
		this.systemRepository = systemRepository;
	}

	@Override
	public List<System> getAllSystems() {
		return systemRepository.findAll();
	}

    @Override
    public System validateAndGetSystem(Long id) {
        return systemRepository.findById(id)
                .orElseThrow(() -> new SystemNotFoundException(String.format("System with id %s not found", id)));
    }
}
