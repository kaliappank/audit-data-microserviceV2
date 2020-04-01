package com.src.microservice.testing.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InterfaceDto {
	private Long interface_id;
	private Long system_id;
    private String interface_name;
    private LocalDateTime created_ts;
}
