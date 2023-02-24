package com.itvedant.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.exceptions.ProductException;
import com.itvedant.models.Product;
import com.itvedant.services.ProductServicesInterface;

@RestController
public class ProductController {

	@Autowired
	ProductServicesInterface service;

	/* CRUD OPERATIONS */

	// 1. Get Products
	@GetMapping("/getproducts")
	public ResponseEntity<List<Product>> getProducts()
	{
		List<Product> products = service.getProducts();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	// 2. Add Product
	@PostMapping("/addproduct")
	public ResponseEntity<String> addProduct(@RequestBody @Valid Product newProduct)
	{
		String msg = service.addProduct(newProduct);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}

	// 3. Update Product
	@PutMapping("/updateproduct")
	public ResponseEntity<String> updateProduct(@RequestBody Product newProduct)
	{
		String msg = service.updateProduct(newProduct);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}

	// 4. Delete Product
	@DeleteMapping("/deleteproduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id)
	{
		String msg = service.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}

	
	
	/* EXCEPTION HANDLING */
	
	@ExceptionHandler(ProductException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> productException(ProductException ex)
	{
		Map<String,String> errorMap = new HashMap<>();
		
		errorMap.put("Error", ex.getMessage());
		return errorMap;
	}
	
	
}
