package com.src.microservice.testing.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CreateAuditDto {
	
	@Positive
	private Long interface_id;
	@Positive
	private Long source_system_id;
	@Positive
	private Long target_system_id;
	@Positive
	private Long project_id;
	@NotBlank
	private String source_reference_id;
	@NotBlank
	private String status;
	
}
