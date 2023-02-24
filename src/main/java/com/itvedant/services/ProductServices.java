package com.itvedant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.exceptions.ProductException;
import com.itvedant.models.Product;
import com.itvedant.repositories.ProductRepo;

@Service
public class ProductServices implements ProductServicesInterface{

	@Autowired
	ProductRepo repo;
	
	@Override
	public List<Product> getProducts()
	{
		List<Product> products = repo.findAll();
		return products;
	}

	@Override
	public String addProduct(Product newProduct)
	{
		Optional<Product> product = repo.findById(newProduct.getId());

		if(product.isPresent())
		{
			throw new ProductException("This product Already Exists");
		}
	
		repo.save(newProduct);
		return "Product added Successfully.";
	}

	@Override
	public String updateProduct(Product newProduct)
	{
		Optional<Product> product = repo.findById(newProduct.getId());

		if(product.isEmpty())
		{
			throw new ProductException("This Product Doesn't Exist");
		}
	
		repo.save(newProduct);
		return "Product Updated Successfully.";
	}

	@Override
	public String deleteProduct(int id)
	{
		Optional<Product> product = repo.findById(id);

		if(product.isEmpty())
		{
			throw new ProductException("This Product Doesn't Exist");
		}
	
		repo.delete(product.get());
		return "Product Deleted Successfully.";
	}

	
}
