package com.itvedant.services;

import java.util.List;

import com.itvedant.models.CustomUser;

public interface Services {

	/* CRUD OPERATIONS */
	
	// 1. Get CustomUsers
	public List<CustomUser> getCustomUsers(); 
	
	// 2. Add User
	public String addUser(CustomUser newUser);
	
	// 3. Add Admin
	public String addAdmin(CustomUser newAdmin);
	
	// 4. Update CustomUser
	public String updateCustomUser(CustomUser newCustomUser);
	
	// 5. Delete CustomUser
	public List<CustomUser> deleteCustomUser(int id);
}
