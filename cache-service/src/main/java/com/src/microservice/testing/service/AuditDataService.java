package com.src.microservice.testing.service;

import java.util.List;

import com.src.microservice.testing.model.AuditData;

public interface AuditDataService {
    List<AuditData> getAllAuditDatas();

    AuditData validateAndGetAuditData(Long id);

    AuditData saveAuditData(AuditData auditData);

//    void deleteInterface(Interface Interface);

}	
