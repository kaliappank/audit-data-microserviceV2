package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.model.System;

public interface SystemService {
    List<System> getAllSystems();

    System validateAndGetSystem(Long id);

}	
