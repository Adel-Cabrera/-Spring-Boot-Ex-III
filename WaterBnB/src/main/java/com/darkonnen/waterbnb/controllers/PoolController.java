package com.darkonnen.waterbnb.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.darkonnen.waterbnb.models.Pool;
import com.darkonnen.waterbnb.models.User;
import com.darkonnen.waterbnb.services.PoolService;
import com.darkonnen.waterbnb.services.ReviewService;
import com.darkonnen.waterbnb.services.UserService;
import com.darkonnen.waterbnb.validators.PoolValidator;

@Controller
public class PoolController {
	private PoolService poolService;
	private PoolValidator poolValidator;
	private UserService userService;
	private ReviewService reviewService;
	
	public PoolController(PoolService poolService, PoolValidator poolValidator, ReviewService reviewService, UserService userService) {
		this.poolService = poolService;
		this.poolValidator = poolValidator;
		this.userService = userService;
		this.reviewService = reviewService;
	}
	
	@RequestMapping("/landing")
	public String landing(Model model, Principal principal) {
		model.addAttribute("principal", principal);
//		System.out.println("***********");		

		return "landingView";
	}
	
	@RequestMapping("/host/dashboard")
	public String showDashboard(Principal principal, Model model, @Valid @ModelAttribute("pool") Pool pool) {
		User currentUser = userService.findByEmail(principal.getName());
		List<Object[]> userListings = poolService.findUserPoolListingsById(currentUser.getId());
		model.addAttribute("userListings", userListings);
		System.out.println("***********");		
		return "dashboardView";
	}
	
	@PostMapping("/createPool")
	public String createPool(Model model, @Valid @ModelAttribute("pool") Pool pool, BindingResult result, Principal principal) {
		poolValidator.validate(pool, result);
		User currentUser = userService.findByEmail(principal.getName());
		List<Object[]> userListings = poolService.findUserPoolListingsById(currentUser.getId());
		
		if (result.hasErrors()) {
			model.addAttribute("userListings", userListings);
			return "dashboardView";
		} else {
			pool.setHost(currentUser);
			poolService.savePool(pool);
			return "redirect:/host/dashboard";
		}
	}
	
	@RequestMapping("/host/pools/{pool_id}")
	public String editPool(@PathVariable("pool_id") Long id, Model model, Principal principal) {
		Pool pool = poolService.findPoolById(id);
		User currentUser = userService.findByEmail(principal.getName());
		List<Object[]> reviews = reviewService.findPoolReviews(pool.getId());
		model.addAttribute("pool", pool);
		model.addAttribute("reviews", reviews);
		model.addAttribute("currentUser", currentUser);
		return "editPoolView";
	}
	
	@PostMapping("/edit")
	public String editPool(@Valid @ModelAttribute("pool") Pool pool, BindingResult result, Model model, Principal principal) {
		poolValidator.validate(pool, result);
		if (result.hasErrors()) {
			User currentUser = userService.findByEmail(principal.getName());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("pool", pool);
			return "editPoolView";
		} else {
			poolService.updatePool(pool);
			return "redirect:/host/pools/"+pool.getId();
		}	
	}
	
	@RequestMapping("/search")
	public String searchPool(Model model, Principal principal) {
		List<Pool> pools = poolService.findAllPools();
		
		if (principal != null) {
			User currentUser = userService.findByEmail(principal.getName());
			model.addAttribute("currentUser", currentUser);
		}
		
		model.addAttribute("pools", pools);
		model.addAttribute("principal", principal);
		return "searchView";
	}
	
	@PostMapping("/search")
	public String searchLocation(Model model, @RequestParam("searchQuery") String searchQuery, Principal principal) {
		if (searchQuery.equals(null)) {
			return "redirect:/search";
		} else {
			List<Pool> pools = poolService.findPoolsByLocation(searchQuery);
			
			if (principal != null) {
				User currentUser = userService.findByEmail(principal.getName());
				model.addAttribute("currentUser", currentUser);
			}
			
			model.addAttribute("pools", pools);
			model.addAttribute("principal", principal);
			return "searchView";
		}
	}
	
	@RequestMapping("/pools/{pool_id}")
	public String showPool(@PathVariable("pool_id") Long id, Model model, Principal principal) {
		Pool pool = poolService.findPoolById(id);
		model.addAttribute("principal", principal);
		model.addAttribute("pool", pool);
		
		if (principal != null) {
			User currentUser = userService.findByEmail(principal.getName());
			model.addAttribute("currentUser", currentUser);
		}
		
		List<Object[]> reviews = reviewService.findPoolReviews(pool.getId());
		model.addAttribute("reviews", reviews);
		return "poolDetailsView";
	}
	
}