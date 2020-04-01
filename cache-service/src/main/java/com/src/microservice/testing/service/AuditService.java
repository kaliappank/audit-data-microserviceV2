package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.model.Audit;

public interface AuditService {
    List<Audit> getAllAudits();

    Audit validateAndGetAudit(Long id);

    Audit saveAudit(Audit audit);

//    void deleteInterface(Interface Interface);

}	
