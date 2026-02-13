package com.info.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.info.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(BaseException.class)
	    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {

	        ErrorResponse error = new ErrorResponse(
	                ex.getMessage(),
	                ex.getErrorCode(),
	                LocalDateTime.now()
	        );

	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		HashMap<String, String> hm = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> hm.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hm);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<HashMap<String, Object>> handleInternalServer(Exception e) {
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("error", e.getMessage());
		return ResponseEntity.internalServerError().body(hm);
	}
}

