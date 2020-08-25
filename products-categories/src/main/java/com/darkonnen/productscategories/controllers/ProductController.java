package com.darkonnen.productscategories.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.darkonnen.productscategories.models.Category;
import com.darkonnen.productscategories.models.Product;
import com.darkonnen.productscategories.services.AppService;

@Controller 
@RequestMapping("products")
public class ProductController {
	private AppService appService;

	public ProductController(AppService appService) {
		this.appService = appService;
	}
	
	@RequestMapping(value="new", method = RequestMethod.GET)
	public String newProduct(@ModelAttribute("product") Product product) {
		return "/products/new.jsp";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String showProduct(@PathVariable("id") Long id, Model model) {
		Product product = appService.findProduct(id);
		List<Category> categories = appService.allCategories();
		List<Category> remCategories = new ArrayList<Category>();
		
		for (Category category : categories) {
			for (Product prodLink : category.getProducts()) {
				if (prodLink.getId().equals(id)) {
					remCategories.add(category);
				}
			}
		}
		
		categories.removeAll(remCategories);
		
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);

		return "/products/show.jsp";
	}
	
	@RequestMapping(value="new", method = RequestMethod.POST)
	public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "/products/new.jsp";
		} else { 
			appService.saveProduct(product);
			return "redirect:/products/" + product.getId(); 
		}
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public String updateProduct(@PathVariable("id") Long prodId, @RequestParam("id") Long catId) {
		Product product = appService.findProduct(prodId);
		Category category = appService.findCategory(catId);
		
		product.getCategories().add(category);
		appService.saveProduct(product);
		
		return "redirect:/products/" + product.getId(); 		
	}
}