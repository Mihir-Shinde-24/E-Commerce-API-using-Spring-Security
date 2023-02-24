package com.itvedant.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.customannotations.LoggedInUser;
import com.itvedant.exceptions.CustomUserDBException;
import com.itvedant.models.CustomUser;
import com.itvedant.services.CustomUserServicesInterface;

@RestController
public class CustomUserController {

	@Autowired
	CustomUserServicesInterface service;
	
	@Autowired
	PasswordEncoder encoder;
		
	/* CRUD OPERATIONS */
	
	// 1. Get Users
	@GetMapping("/getusers")
	public ResponseEntity<List<CustomUser>> getCustomUsers()
	{
		List<CustomUser> customUsers = service.getCustomUsers();
		return ResponseEntity.status(HttpStatus.OK).body(customUsers);
	}

	// 2. Add User
	@PostMapping("/adduser")
	public ResponseEntity<String> addUser(@RequestBody CustomUser newUser)
	{
		newUser.setPasscode(encoder.encode(newUser.getPasscode()));
		String msg = service.addUser(newUser);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	// 3. Add Admin
	@PostMapping("/addadmin")
	public ResponseEntity<String> addAdmin(@RequestBody CustomUser newAdmin)
	{
		newAdmin.setPasscode(encoder.encode(newAdmin.getPasscode()));
		String msg = service.addAdmin(newAdmin);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}

	// 4. Update User
	@PutMapping("/updateuser")
	public ResponseEntity<String> updateCustomUser(@RequestBody CustomUser newUser, @LoggedInUser CustomUser loggedInUser)
	{
		if(loggedInUser.getId() == newUser.getId())
		{
			newUser.setPasscode(encoder.encode(newUser.getPasscode()));
			String msg = service.updateCustomUser(newUser);
			return ResponseEntity.status(HttpStatus.OK).body(msg);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select your id only.");
	}

	// 5. Delete User
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id, @LoggedInUser CustomUser loggedInUser)
	{
		if(loggedInUser.getRole().equals("ROLE_USER"))
		{
			if(loggedInUser.getId() == id)
			{
				String msg = service.deleteUser(id);
				return ResponseEntity.status(HttpStatus.OK).body(msg);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select your id only.");
		}
		else 
		{
			String msg = service.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body(msg);
		}
		
		
		
	}
	// 6. Delete Admin
	@DeleteMapping("/deleteadmin/{id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("id") int id)
	{
		String msg = service.deleteAdmin(id);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	
	/* EXCEPTION HANDLING */
	
	@ExceptionHandler(CustomUserDBException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> customUserDBException(CustomUserDBException ex)
	{
		Map<String,String> errorMap = new HashMap<>();
		
		errorMap.put("Error", ex.getMessage());
		return errorMap;
	}
}
