package com.itvedant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
