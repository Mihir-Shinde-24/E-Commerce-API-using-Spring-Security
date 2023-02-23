package com.itvedant.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOM_USERS")
public class CustomUser {

	/* Fields */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String passcode;
	
	@Column
	private String role;
	
	
	
	
	/* Constructors */
	
	public CustomUser()	{}

	
	
	
	/* Getters & Setters */
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPasscode()
	{
		return passcode;
	}

	public void setPasscode(String passcode)
	{
		this.passcode = passcode;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

}
