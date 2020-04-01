package com.src.microservice.testing.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AuditDto {
	private Long audit_id;
	private Long interface_id;
	private Long source_system_id;
	private Long target_system_id;
	private String source_reference_id;
	private Long project_id;
    private LocalDateTime created_ts;
    private String status;
}
