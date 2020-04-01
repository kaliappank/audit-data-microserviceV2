package com.src.microservice.testing.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SystemDto {
	private Long system_id;
	//private ProjectDto project;
	private Long project_id;
	
    private String system_name;
	private LocalDateTime created_ts;
}
