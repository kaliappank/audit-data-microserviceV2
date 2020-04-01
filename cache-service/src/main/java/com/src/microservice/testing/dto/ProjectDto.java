package com.src.microservice.testing.dto;

import java.time.LocalDateTime;

import lombok.Data;
import java.util.List;

@Data
public class ProjectDto {
	private Long project_id;
    private String project_name;
	private LocalDateTime created_ts;
	//private List<SystemDto> systems;
}
