package com.darkonnen.posts.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.darkonnen.posts.models.Post;
import com.darkonnen.posts.models.Role;
import com.darkonnen.posts.models.User;
import com.darkonnen.posts.services.PostService;
import com.darkonnen.posts.services.UserService;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	@Autowired 
	private PostService postService;
	
	@Autowired 
	private UserService userService;
	
	// READ ALL
	
	@GetMapping("")
	public String homePage(@Valid @ModelAttribute("posts") Post post, Principal principal, Model model) {
		String email = principal.getName();
        User currentUser = userService.findUserByEmail(email);
		List<Post> allPosts = postService.findAllPost();
		List<Post> currentUserPosts = currentUser.getUserPosts();
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("currentUserPosts", currentUserPosts);
		model.addAttribute("allPosts", allPosts);

		List<Role> userRoles = currentUser.getRoles();
		model.addAttribute("userRoles", userRoles);

		return "home";
	}
	
	// READ ONE
	
	@GetMapping("/{id}")
	public String showPost(@PathVariable("id") Long id, Model model) {
		
		// Encontrar Post por ID
		Post post = postService.findPostById(id);

		// Encontrar creador del post
		User user = post.getCreatorPost();
		
		// Enviar el post y el creador a la vista con Model 
		model.addAttribute("creatorPost", user);
		model.addAttribute("post", post);
		
		// Retornar vista
		return "show";
	}
	
	// CREATE NEW	
	@GetMapping("/new")
	public String newPost(Model model) {
		Post post = new Post();
		model.addAttribute("newPost", post);
		return "new";
	}
	
	// CREATE SAVE
    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("newPost") Post post, Principal principal, BindingResult result) {
        if(result.hasErrors()) {        	
            return "home";
        }
        else {        	
            String email = principal.getName();
            User creatorPost = userService.findUserByEmail(email);            
            post.setCreatorPost(creatorPost); // Setea al creador del post
            postService.createPost(post);
            return "redirect:/posts";
        }
    }
	
	
	// UPDATE - EDIT    
	
	@GetMapping(value="/edit/{id}")
	public String editPost(@PathVariable("id") Long id, Model model) { 
		Post post = postService.findPostById(id);
		model.addAttribute("editPost", post); // OBJETO PARA EDITAR
		return "edit";
	}
	
//    @RequestMapping(value="/edit/{id}", method=RequestMethod.PUT)
    @PostMapping(value="/edit/{id}")
    public String updatePost(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("editPost") Post editedPost, Principal principal, BindingResult result) {
    	Post post = postService.findPostById(id);
        String email = principal.getName();
        User creatorPost = userService.findUserByEmail(email);                	
    	model.addAttribute("post", post);
    	if(result.hasErrors()) {
    		return "redirect:/posts/edit/" + post.getId();
//    		return "edit"; //// AMBOS SIRVEN
        }
        else {
        	editedPost.setCreatorPost(creatorPost);        	
        	postService.updatePost(editedPost);
        	return "redirect:/posts";
        }
    }
    	
	// DELETE - DESTROY
	
	@RequestMapping(value="/delete/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		postService.deletePost(id);
		return "redirect:/posts";
	}
    

}
