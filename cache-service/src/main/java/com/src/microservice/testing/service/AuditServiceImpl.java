package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.exception.AuditNotFoundException;
import com.src.microservice.testing.model.Audit;
import com.src.microservice.testing.repository.AuditRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

	private final AuditRepository auditRepository;

	public AuditServiceImpl(AuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}

	@Override
	public List<Audit> getAllAudits() {
		return auditRepository.findAll();
	}

    @Override
    public Audit validateAndGetAudit(Long id) {
        return auditRepository.findById(id)
                .orElseThrow(() -> new AuditNotFoundException(String.format("Audit with id %s not found", id)));
    }
    
    @Override
	public Audit saveAudit(Audit audit) {
		return auditRepository.save(audit);
	}
}
