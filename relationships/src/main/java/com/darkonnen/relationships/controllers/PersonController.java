package com.darkonnen.relationships.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.darkonnen.relationships.models.Person;
import com.darkonnen.relationships.services.PersonService;


@Controller 
@RequestMapping("persons")
public class PersonController {
	
	private final PersonService personService;
	
	public PersonController(PersonService pService) {
		this.personService = pService;
	}
	
	@RequestMapping(value="new", method=RequestMethod.GET)
	public String newPerson(@ModelAttribute("person") Person person) {
		return "persons/new.jsp";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String showPerson(@PathVariable("id") Long id, Model model) {
		Person person = personService.findPerson(id);
		model.addAttribute("person", person);
		
		return "persons/show.jsp";
	}	
	
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String addNewPerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		if (result.hasErrors()) {
			return "persons/new.jsp"; 
		} else {
			personService.createPerson(person);
			return "redirect:/persons/new";
		}
	}
}