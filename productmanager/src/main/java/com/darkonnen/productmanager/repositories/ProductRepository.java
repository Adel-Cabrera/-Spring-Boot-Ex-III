package com.darkonnen.productmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darkonnen.productmanager.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
