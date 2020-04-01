package com.src.microservice.testing.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AuditDataDto {
	private Long audit_data_id;
	private Long audit_id;
	private String source_reference_id;
	private String request_data;
	private String response_data;
    private LocalDateTime created_ts;
}
