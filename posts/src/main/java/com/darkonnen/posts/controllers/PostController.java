package com.darkonnen.posts.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.darkonnen.posts.models.Post;
import com.darkonnen.posts.services.PostService;
import com.darkonnen.posts.services.UserService;

@Controller
public class PostController {
	
	@Autowired 
	private PostService postService;
	
	@Autowired 
	private UserService userService;
	
	// READ ALL
	
	@RequestMapping("/posts")
	public String homePage(Principal principal, Model model, @Valid @ModelAttribute("post") Post post) {
		return "posts";
	}
	
	// READ ONE
	
	

}
