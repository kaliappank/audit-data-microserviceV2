package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.exception.InterfaceNotFoundException;
import com.src.microservice.testing.model.Interface;
import com.src.microservice.testing.repository.InterfaceRepository;
import org.springframework.stereotype.Service;

@Service
public class InterfaceServiceImpl implements InterfaceService {

	private final InterfaceRepository interfaceRepository;

	public InterfaceServiceImpl(InterfaceRepository interfaceRepository) {
		this.interfaceRepository = interfaceRepository;
	}

	@Override
	public List<Interface> getAllInterfaces() {
		return interfaceRepository.findAll();
	}

    @Override
    public Interface validateAndGetInterface(Long id) {
        return interfaceRepository.findById(id)
                .orElseThrow(() -> new InterfaceNotFoundException(String.format("Interface with id %s not found", id)));
    }
}
