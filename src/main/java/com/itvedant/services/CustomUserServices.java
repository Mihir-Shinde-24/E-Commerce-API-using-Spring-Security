package com.itvedant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itvedant.exceptions.CustomUserDBException;
import com.itvedant.models.CustomUser;
import com.itvedant.repositories.CustomUserRepo;

@Service
public class CustomUserServices implements CustomUserServicesInterface, UserDetailsService {

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
			throw new CustomUserDBException("This User Already Exists");
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
			throw new CustomUserDBException("This Admin Already Exists"); 
		}
		newAdmin.setRole("ROLE_ADMIN");
		repo.save(newAdmin);
		return "Admin added Successfully.";
	}

	@Override
	public String updateCustomUser(CustomUser newUser)
	{	
		Optional<CustomUser> user = repo.findById(newUser.getId());

		if (user.isPresent() && user.get().getRole().equals("ROLE_USER") )
		{
			newUser.setRole("ROLE_USER");
			repo.save(newUser);
			return "User Updated Successfully.";
		}
		throw new CustomUserDBException("This User Doesn't Exist.");
		
	}

	@Override
	public String deleteUser(int id)
	{
		Optional<CustomUser> user = repo.findById(id);

		if (user.isPresent() && user.get().getRole().equals("ROLE_USER") )
		{
			repo.delete(user.get());
			return "User Deleted Successfully.";
		}
		throw new CustomUserDBException("This User Doesn't Exist.");
	}

	@Override
	public String deleteAdmin(int id)
	{
		Optional<CustomUser> admin = repo.findById(id);

		if (admin.isPresent() && admin.get().getRole().equals("ROLE_ADMIN"))
		{
			repo.delete(admin.get());
			return "Admin Deleted Successfully.";
		}
		throw new CustomUserDBException("This Admin Doesn't Exist.");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return repo.findByEmail(username).get();
	}

}
