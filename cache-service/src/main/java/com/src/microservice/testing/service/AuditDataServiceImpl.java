package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.exception.AuditDataNotFoundException;
import com.src.microservice.testing.model.AuditData;
import com.src.microservice.testing.repository.AuditDataRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditDataServiceImpl implements AuditDataService {

	private final AuditDataRepository auditDataRepository;

	public AuditDataServiceImpl(AuditDataRepository auditDataRepository) {
		this.auditDataRepository = auditDataRepository;
	}

	@Override
	public List<AuditData> getAllAuditDatas() {
		return auditDataRepository.findAll();
	}

    @Override
    public AuditData validateAndGetAuditData(Long id) {
        return auditDataRepository.findById(id)
                .orElseThrow(() -> new AuditDataNotFoundException(String.format("AuditData with id %s not found", id)));
    }
    
    @Override
	public AuditData saveAuditData(AuditData auditData) {
		return auditDataRepository.save(auditData);
	}
}
