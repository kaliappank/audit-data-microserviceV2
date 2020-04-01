package com.src.microservice.testing.repository;

import com.src.microservice.testing.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuditRepository extends JpaRepository<Audit, Long> {
	
}
