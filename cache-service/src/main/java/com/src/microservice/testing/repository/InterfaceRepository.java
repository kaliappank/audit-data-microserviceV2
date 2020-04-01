package com.src.microservice.testing.repository;

import com.src.microservice.testing.model.Interface;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterfaceRepository extends JpaRepository<Interface, Long> {
	
}
