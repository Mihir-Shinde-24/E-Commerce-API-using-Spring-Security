package com.itvedant.services;

import java.util.List;

import com.itvedant.models.Product;

public interface ProductServicesInterface {

	/* CRUD OPERATIONS */

	// 1. Get Products
	public List<Product> getProducts();

	// 2. Add Product
	public String addProduct(Product newProduct);

	// 3. Update Product
	public String updateProduct(Product newProduct);

	// 4. Delete Products
	public String deleteProduct(int id);

}
