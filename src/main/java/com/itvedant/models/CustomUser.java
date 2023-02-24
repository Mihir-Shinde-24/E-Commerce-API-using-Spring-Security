package com.itvedant.models;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "CUSTOM_USERS")
public class CustomUser implements UserDetails {

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

	public CustomUser()
	{
	}

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
	
	/* Methods */
	
	@Override
	public String toString()
	{
		return "CustomUser [id=" + id + ", name=" + name + ", email=" + email + ", passcode=" + passcode + ", role="
				+ role + "]";
	}

	/* AUTH METHODS */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		HashSet<SimpleGrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(this.role));
		
		return roles;
		
	}

	@Override
	public String getPassword()
	{
		return this.passcode;
	}

	@Override
	public String getUsername()
	{
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	
	
	

}
