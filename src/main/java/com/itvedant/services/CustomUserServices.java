package com.itvedant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.exceptions.CustomUserAlreadyExistsException;
import com.itvedant.models.CustomUser;
import com.itvedant.repositories.CustomUserRepo;

@Service
public class CustomUserServices implements Services {

	@Autowired
	CustomUserRepo repo;

	/* CRUD OPERATIONS */

	@Override
	public List<CustomUser> getCustomUsers()
	{
		List<CustomUser> customUsers = repo.findAll();
		return customUsers;
	}

	@Override
	public String addUser(CustomUser newUser)
	{
		Optional<CustomUser> user = repo.findByEmail(newUser.getEmail());

		if (user.isPresent())
		{
			throw new CustomUserAlreadyExistsException("This User Already Exists");
		}
		newUser.setRole("ROLE_USER");
		repo.save(newUser);
		return "User added Successfully.";
	}

	@Override
	public String addAdmin(CustomUser newAdmin)
	{
		Optional<CustomUser> admin = repo.findByEmail(newAdmin.getEmail());

		if (admin.isPresent())
		{
			throw new CustomUserAlreadyExistsException("This Admin Already Exists"); 
		}
		newAdmin.setRole("ROLE_ADMIN");
		repo.save(newAdmin);
		return "Admin added Successfully.";
	}

	@Override
	public String updateCustomUser(CustomUser newCustomUser)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomUser> deleteCustomUser(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
