package com.itvedant.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> methodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> errorMap = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors()
							 .forEach(err -> errorMap.put(err.getField(), err.getDefaultMessage()));
							
		return errorMap;
	}
}
