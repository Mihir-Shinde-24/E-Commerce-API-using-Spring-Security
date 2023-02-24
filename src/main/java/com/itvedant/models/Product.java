package com.itvedant.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "PRODUCTS")
public class Product {

	/* Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column
	@NotBlank
	String category;

	@Column
	@NotBlank
	String brand;

	@Column
	@NotBlank
	String model;

	@Column
	@Min(value = 100)
	double price;

	@Column
	@Digits(integer = 1, fraction = 1, message = "Invalid Rating Out of 5. Eg: 4.5 , 2.3 ")
	@Max(value = 5)
	@Min(value = 1)
	BigDecimal rating;

	/* Constructors */
	public Product()
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

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public BigDecimal getRating()
	{
		return rating;
	}

	public void setRating(BigDecimal rating)
	{
		this.rating = rating;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

}
