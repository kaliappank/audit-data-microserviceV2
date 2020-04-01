package com.src.microservice.testing.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class UpdateSystemDto {
//	@NotBlank
	@Positive
	private Long project_id;
	@NotBlank
	private String system_name;
}