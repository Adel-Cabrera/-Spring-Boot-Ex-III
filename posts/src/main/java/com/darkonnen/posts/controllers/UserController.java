package com.darkonnen.posts.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.darkonnen.posts.models.User;
import com.darkonnen.posts.services.UserService;
import com.darkonnen.posts.validators.UserValidator;

@Controller
public class UserController {

	private UserValidator userValidator;

	private UserService userService;
	
	public UserController(UserValidator userValidator, UserService userService) {
		this.userValidator = userValidator;
		this.userService = userService;
	} 

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model,
			@Valid @ModelAttribute("user") User user, BindingResult result) {
//		System.out.println(user.toString() + "from /login");
		if (error != null) {
			model.addAttribute("errorMessage", "Invalid credentials, please try again.");
		}
		if (logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		
		return "login";
	}

	@GetMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user") User user) {
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "registration";
		} else {
			userService.saveWithUserRole(user);
//			userService.saveUserWithAdminRole(user);
			return "redirect:/login";
		}
	}

	@RequestMapping(value = { "/", "/home" })
	public String home(Principal principal, Model model) {
		return "redirect:/posts"; // -> REDIRECT TO HOME CONTROLLER
	}

	@GetMapping("/admin")
	public String adminPage(Principal principal, Model model) {
		String email = principal.getName();
        User admin = userService.findUserByEmail(email);
        model.addAttribute("admin", admin);
		return "admin";
	}
	
}