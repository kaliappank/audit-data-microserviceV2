package com.src.microservice.testing.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;

@Data
public class CreateAuditDataDto {
	
	@Positive
	private Long audit_id;
	@NotBlank
	private String source_reference_id;
	@NotBlank
	private String request_data;
	@NotNull
	private String response_data;
	
}
