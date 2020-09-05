package com.darkonnen.waterbnb.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.darkonnen.waterbnb.models.Pool;
import com.darkonnen.waterbnb.models.Review;
import com.darkonnen.waterbnb.models.User;
import com.darkonnen.waterbnb.services.PoolService;
import com.darkonnen.waterbnb.services.ReviewService;
import com.darkonnen.waterbnb.services.UserService;
import com.darkonnen.waterbnb.validators.ReviewValidator;

@Controller
public class ReviewController {
	private ReviewService reviewService;
	private ReviewValidator reviewValidator;
	private PoolService poolService;
	private UserService userService;
	
	public ReviewController(ReviewService reviewService, ReviewValidator reviewValidator, UserService userService, PoolService poolService) {
		this.reviewService = reviewService;
		this.reviewValidator = reviewValidator;
		this.poolService = poolService;
		this.userService = userService;
	}
	
	@RequestMapping("/pools/{pool_id}/review")
	public String reviewPage(Model model, @PathVariable("pool_id") Long id, Principal principal, @ModelAttribute("reviewObj") Review review) {
		Pool pool = poolService.findPoolById(id);
		User currentUser = userService.findByEmail(principal.getName());
		model.addAttribute("pool", pool);
		model.addAttribute("currentUser", currentUser);
		return "reviewView";
	}
	
	@PostMapping("/addReview")
	public String addReview(Model model, @Valid @ModelAttribute("reviewObj") Review review, BindingResult result) {
		reviewValidator.validate(review, result);
		
		if (result.hasErrors()) {
			model.addAttribute("review", review);
			return "reviewView";
		} else {
			reviewService.addReview(review);
			poolService.updateRating(review.getPool().getId());
			
			return "redirect:/pools/"+review.getPool().getId();
		}
	}
	
}