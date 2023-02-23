package com.itvedant.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.exceptions.CustomUserAlreadyExistsException;
import com.itvedant.models.CustomUser;
import com.itvedant.services.Services;

@RestController
public class CustomUserController {

	@Autowired
	Services service;
		
	/* CRUD OPERATIONS */
	
	@GetMapping("/getusers")
	public ResponseEntity<List<CustomUser>> getCustomUsers()
	{
		List<CustomUser> customUsers = service.getCustomUsers();
		return ResponseEntity.status(HttpStatus.OK).body(customUsers);
	}

	@PostMapping("/adduser")
	public ResponseEntity<String> addUser(@RequestBody CustomUser newUser)
	{
		String msg = service.addUser(newUser);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@PostMapping("/addadmin")
	public ResponseEntity<String> addAdmin(@RequestBody CustomUser newAdmin)
	{
		String msg = service.addAdmin(newAdmin);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}

	public ResponseEntity<String> updateCustomUser()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<List<CustomUser>> deleteCustomUser()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/* EXCEPTION HANDLING */
	
	@ExceptionHandler(CustomUserAlreadyExistsException.class)
	public Map<String,String> customUserAlreadyExistsException(CustomUserAlreadyExistsException ex)
	{
		Map<String,String> errorMap = new HashMap<>();
		
		errorMap.put("Error", ex.getMessage());
		return errorMap;
	}
}
