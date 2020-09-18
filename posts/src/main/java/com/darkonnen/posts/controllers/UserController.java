package com.darkonnen.posts.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.darkonnen.posts.models.User;
import com.darkonnen.posts.services.UserService;
import com.darkonnen.posts.validators.UserValidator;


@Controller
public class UserController {
	
	@Autowired
    private UserValidator userValidator;
    
    @Autowired
    private UserService userService;    


    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
    	if(logout != null) {
            model.addAttribute("logout", "Logout Successful!");
        }
        if(error != null) {
//        	System.out.println(result.getAllErrors());
            model.addAttribute("logError", "Invalid credentials, please try again.");
        }
        return "index";
    }
        
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
    	userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "index";
        }
        else {
        	userService.createUser(user);
            return "redirect:/login";
        }
    }   

    @RequestMapping(value= {"/", "/home"})
    public String index() {
    	return "redirect:/posts";
    }
}