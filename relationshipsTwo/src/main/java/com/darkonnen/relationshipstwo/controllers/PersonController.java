package com.darkonnen.relationshipstwo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.darkonnen.relationshipstwo.models.Person;
import com.darkonnen.relationshipstwo.services.PersonService;

@Controller
@RequestMapping("persons")
public class PersonController {
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	// READ ALL
	
	@GetMapping("") 
	public ModelAndView index() { // -> ModelAndView
		ModelAndView mav = new ModelAndView("index"); // template name
		mav.addObject("persons", personService.allPeople());
		return mav;
	}
	
	
	// READ ONE
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Person person = personService.findPerson(id);

		model.addAttribute("person", person);

		return "show";
	}
	
	
	// NEW
	
	@RequestMapping("/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		return "new";

	}
	
	// CREATE

}
