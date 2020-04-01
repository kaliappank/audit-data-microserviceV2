package com.src.microservice.testing.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.src.microservice.testing.dto.AuditDataDto;
import com.src.microservice.testing.dto.CreateAuditDataDto;
import com.src.microservice.testing.model.AuditData;
import com.src.microservice.testing.service.AuditDataService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.glasnost.orika.MapperFacade;

@RestController
@RequestMapping("api/audit-datas")
public class AuditDataController {
	
	private final AuditDataService auditDataService;
	private final MapperFacade mapperFacade;

	public AuditDataController(AuditDataService auditDataService, MapperFacade mapperFacade) {
		this.auditDataService = auditDataService;
		this.mapperFacade = mapperFacade;
	}

	@GetMapping
	public List<AuditDataDto> getAllAuditDatas(){
		return auditDataService.getAllAuditDatas().stream().map(audit -> mapperFacade.map(audit, AuditDataDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{audit_data_id}")
	public AuditDataDto getAuditData(@PathVariable Long audit_data_id) {
		AuditData auditData = auditDataService.validateAndGetAuditData(audit_data_id);
		return mapperFacade.map(auditData, AuditDataDto.class);
	}	

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public AuditDataDto createAudit(@Valid @RequestBody CreateAuditDataDto createAuditDataDto) {
		AuditData auditData = mapperFacade.map(createAuditDataDto, AuditData.class);
		auditData = auditDataService.saveAuditData(auditData);
		return mapperFacade.map(auditData, AuditDataDto.class);
	}
}