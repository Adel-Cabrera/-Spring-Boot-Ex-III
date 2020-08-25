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
@RequestMapping("categories")
public class CategoryController {
	private AppService appService;

	public CategoryController(AppService appService) {
		this.appService = appService;
	}
	
	@RequestMapping(value="new", method = RequestMethod.GET)
	public String newCategory(@ModelAttribute("category") Category category) {
		return "/categories/new.jsp";
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public String showCategory(@PathVariable("id") Long id, Model model) {
		Category category = appService.findCategory(id);
		List<Product> products = appService.allProducts();
		List<Product> remProducts = new ArrayList<Product>();
		
		for (Product product : products) {
			for (Category catLink : product.getCategories()) {
				if (catLink.getId().equals(id)) {
					remProducts.add(product);
				}
			}
		}
		
		products.removeAll(remProducts);
		
		model.addAttribute("category", category);
		model.addAttribute("products", products);

		return "/categories/show.jsp";
	}
	
	@RequestMapping(value="new", method = RequestMethod.POST)
	public String addNewCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "/categories/new.jsp";
		} else { 
			appService.saveCategory(category);
			return "redirect:/categories/" + category.getId(); 
		}
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public String updateProduct(@PathVariable("id") Long catId, @RequestParam("id") Long prodId) {
		Category category = appService.findCategory(catId);
		Product product = appService.findProduct(prodId);
		
		category.getProducts().add(product);
		appService.saveCategory(category);
		
		return "redirect:/categories/" + category.getId(); 		
	}
}