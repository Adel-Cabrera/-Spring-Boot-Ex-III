package com.darkonnen.productscategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.productscategories.models.Category;
import com.darkonnen.productscategories.models.Product;
import com.darkonnen.productscategories.repositories.CategoryRepository;
import com.darkonnen.productscategories.repositories.ProductRepository;

@Service
public class AppService {
	private CategoryRepository catRepo;
	private ProductRepository prodRepo;
	
	public AppService(CategoryRepository catRepo, ProductRepository prodRepo) {
		this.catRepo = catRepo;
		this.prodRepo = prodRepo;
	}
	
	public List<Category> allCategories() {
		return catRepo.findAll();
	}
	
	public List<Product> allProducts() {
		return prodRepo.findAll();
	}
	
	public Category findCategory(Long id) {
		Optional<Category> opCat = catRepo.findById(id);
		if(opCat.isPresent()) {
			return opCat.get();
		} else {
			return null;
		}
	}
	
	public Product findProduct(Long id) {
		Optional<Product> opProd = prodRepo.findById(id);
		if(opProd.isPresent()) {
			return opProd.get();
		} else {
			return null;
		}
	}
	
	public Category saveCategory(Category c) {
		return catRepo.save(c);
	}
	
	public Product saveProduct(Product p) {
		return prodRepo.save(p);
	}
}