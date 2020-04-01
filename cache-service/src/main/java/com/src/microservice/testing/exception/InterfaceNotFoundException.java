package com.src.microservice.testing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InterfaceNotFoundException extends RuntimeException{

	public InterfaceNotFoundException(String message) {
		super(message);
	}
	
	
}
