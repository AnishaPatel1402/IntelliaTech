package com.info.ordermangmentsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;

}
