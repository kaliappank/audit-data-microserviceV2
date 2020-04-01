package com.src.microservice.testing.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.src.microservice.testing.dto.AuditDto;
import com.src.microservice.testing.dto.CreateAuditDto;
import com.src.microservice.testing.model.Audit;
import com.src.microservice.testing.service.AuditService;
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
@RequestMapping("api/audits")
public class AuditController {
	
	private final AuditService auditService;
	private final MapperFacade mapperFacade;

	public AuditController(AuditService auditService, MapperFacade mapperFacade) {
		this.auditService = auditService;
		this.mapperFacade = mapperFacade;
	}

	@GetMapping
	public List<AuditDto> getAllAudits(){
		return auditService.getAllAudits().stream().map(audit -> mapperFacade.map(audit, AuditDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{audit_id}")
	public AuditDto getAudit(@PathVariable Long audit_id) {
		Audit audit = auditService.validateAndGetAudit(audit_id);
		return mapperFacade.map(audit, AuditDto.class);
	}	

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public AuditDto createAudit(@Valid @RequestBody CreateAuditDto createAuditDto) {
		Audit audit = mapperFacade.map(createAuditDto, Audit.class);
		audit = auditService.saveAudit(audit);
		return mapperFacade.map(audit, AuditDto.class);
	}
}