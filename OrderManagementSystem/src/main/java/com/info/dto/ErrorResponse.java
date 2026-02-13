package com.info.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;
	public ErrorResponse(String message, String errorCode, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.timestamp = timestamp;
	}
	public ErrorResponse() {
		super();
	}
    
    
}
