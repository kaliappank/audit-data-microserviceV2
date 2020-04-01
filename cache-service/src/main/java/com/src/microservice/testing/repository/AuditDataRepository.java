package com.src.microservice.testing.repository;

import com.src.microservice.testing.model.AuditData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuditDataRepository extends JpaRepository<AuditData, Long> {
	
}
