package com.itvedant.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.models.CustomUser;

public interface CustomUserRepo extends JpaRepository<CustomUser,Integer>{

	// 1. Find By Email
	public Optional<CustomUser> findByEmail(String email);
}
