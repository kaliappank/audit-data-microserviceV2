package com.src.microservice.testing.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CreateInterfaceDto {
	@Positive
	private Long system_id;
	@NotBlank
	private String interface_name;
}
