package com.itvedant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.itvedant.services.CustomUserServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserServices service;

	@Bean
	PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(service).passwordEncoder(encoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET,"/getusers").hasAnyRole("SUPER","ADMIN")
			.antMatchers(HttpMethod.POST,"/addadmin").hasAnyRole("SUPER")
			.antMatchers(HttpMethod.POST,"/adduser").permitAll()
			.antMatchers(HttpMethod.PUT,"/updateuser").hasAnyRole("USER")
			.antMatchers(HttpMethod.DELETE,"/deleteadmin").hasAnyRole("SUPER")
			.antMatchers(HttpMethod.DELETE,"/deleteuser").hasAnyRole("SUPER","ADMIN","USER")
			
			.antMatchers(HttpMethod.GET, "/getproducts").permitAll()			
			.antMatchers(HttpMethod.POST,"/addproduct").hasAnyRole("SUPER","ADMIN")
			.antMatchers(HttpMethod.PUT,"/updateproduct").hasAnyRole("SUPER","ADMIN")
			.antMatchers(HttpMethod.DELETE,"/deleteproduct").hasAnyRole("SUPER","ADMIN")
			
			.and()
			.httpBasic();
			
	}

}
