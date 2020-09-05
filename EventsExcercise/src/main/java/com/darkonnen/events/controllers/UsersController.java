package com.darkonnen.events.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.darkonnen.events.models.Event;
import com.darkonnen.events.models.Message;
import com.darkonnen.events.models.User;
import com.darkonnen.events.services.EventService;
import com.darkonnen.events.services.UserService;
import com.darkonnen.events.validators.UserValidator;

@Controller
public class UsersController {
    private UserService userService;
    private UserValidator userValidator;
    private EventService eventService;    
    
    public UsersController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
       
    }
    
    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    @RequestMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }    
        
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "registrationPage.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("userId", u.getId());
        return "redirect:/home";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		return "redirect:/home";
    	}else {
    		model.addAttribute("user", new User());
    		model.addAttribute("error", "Invalid Credentials. Please try again.");
    		return "index.jsp";
    	}
    }
    
    @RequestMapping("/home")
    public String home(HttpSession session, Model model,@ModelAttribute("event") Event myEvent) {
    	Long userId = (Long) session.getAttribute("userId");
    	User u = userService.findUserById(userId);
    	model.addAttribute("user",u);
    	return "homePage.jsp";
    	}
    
    
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    @RequestMapping("/events")
    public String events(Principal principal, Model model, HttpSession session, @Valid @ModelAttribute("newEvent") Event newEvent) {
    	session.setAttribute("successReg", false);
        String email = principal.getName();
        User user = userService.findByEmail(email).get(0);
        this.userService.update(user);
        model.addAttribute("currentUser", user);
        model.addAttribute("events", this.eventService.getAll());
        model.addAttribute("states", states);
        return "events";
    }
    
    @RequestMapping(value="/events", method=RequestMethod.POST)
    public String newEvent(@Valid @ModelAttribute("newEvent") Event event, Principal principal, BindingResult result, Model model) {
        String email = principal.getName();
        User user = userService.findByEmail(email).get(0);
        this.userService.update(user);
    	if(result.hasErrors()) {
    		return "events";
    	}
    	else {
    		this.eventService.saveEvent(user, event);
    		return "redirect:/events";
    	}
    }

    @RequestMapping("/events/{id}/join")
    public String joinEvent(@PathVariable("id") long id, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email).get(0);
    	this.eventService.joinEvent(user, id);
    	return "redirect:/events";
    }
    
    @RequestMapping("/events/{id}/cancel")
    public String cancelEvent(@PathVariable("id") long id, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email).get(0);
    	this.eventService.leaveEvent(user, id);
    	return "redirect:/events";
    }
   
    @RequestMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable("id") long id) {
    	this.eventService.deleteEvent(id);
    	return "redirect:/events";
    }
    
    @RequestMapping("/events/{id}/edit")
    public String editEvent(@PathVariable("id") long id, Model model) {
    	model.addAttribute("event", this.eventService.getEventById(id));
    	model.addAttribute("states", states);
    	return "edit";
    }

    @RequestMapping(value="/events/{id}/edit", method=RequestMethod.POST)
    public String editEventSubmit(@PathVariable("id") long id, @Valid @ModelAttribute("event") Event event, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		return "edit";
    	}
    	else {
    		this.eventService.editEvent(id, event);
    		return "redirect:/events";	
    	}
    }
    
    @RequestMapping(value="/events/{id}", method=RequestMethod.GET)
    public String event(@PathVariable("id") long id, Model model) {
    	model.addAttribute("event", this.eventService.getEventById(id));
    	return "event";
    }
    
    @RequestMapping(value="/events/{id}", method=RequestMethod.POST)
    public String addComment(@PathVariable("id") long id, Principal principal, @RequestParam(value="text", required=true) String text) {
    	if(text.length() == 0) {
    		return "event";
    	}
    	else {
            String email = principal.getName();
            User user = userService.findByEmail(email).get(0);
            Message message = new Message();
            message.setContent(text);
    		this.eventService.addMessage(message, id, user);
    		return "redirect:/events/" + id;	
    	}
    }    
}