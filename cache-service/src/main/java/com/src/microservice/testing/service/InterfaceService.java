package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.model.Interface;

public interface InterfaceService {
    List<Interface> getAllInterfaces();

    Interface validateAndGetInterface(Long id);

//    Interface saveInterface(Interface Interface);

//    void deleteInterface(Interface Interface);

}	
