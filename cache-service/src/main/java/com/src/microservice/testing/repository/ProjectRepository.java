package com.src.microservice.testing.repository;

import com.src.microservice.testing.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {
	
}
