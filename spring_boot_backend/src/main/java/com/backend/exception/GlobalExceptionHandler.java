package com.backend.exception;



import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.experimental.StandardException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex)
	{
		Map<String, String> errors = new HashMap() ;
		ex.getBindingResult().getAllErrors().forEach(error ->
		{
			  errors.put(((FieldError) error).getField(), error.getDefaultMessage());
		});
		
		Map<String , Object> response = new HashMap() ;
		response.put("message", "validation faield") ;
		response.put("error", errors) ;
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(ResoreseExistException.class)
	public ResponseEntity<Map<String, Object>> resoreseExist(ResoreseExistException ex)
	{
		Map<String, Object> response = new HashMap() ;
		response.put("message", "reasaurse alrady exist") ;
		response.put("error", ex.getMessage()) ;
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response) ;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> resoreseExist(Exception ex)
	{
		Map<String, Object> response = new HashMap() ;
		response.put("message", "internal serer eroor") ;
		response.put("error", ex.getMessage()) ;
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response) ;
	}
	
}
