package com.src.microservice.testing.repository;

import com.src.microservice.testing.model.System;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SystemRepository extends JpaRepository<System, Long> {
	
}
