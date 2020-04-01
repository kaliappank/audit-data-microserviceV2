package com.src.microservice.testing.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class CreateProjectDto {
	//@NotBlank
	//private Long project_id;
	@NotBlank
	private String project_name;
}
