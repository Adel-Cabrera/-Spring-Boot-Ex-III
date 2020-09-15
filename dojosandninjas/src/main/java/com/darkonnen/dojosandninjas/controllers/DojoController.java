package com.darkonnen.dojosandninjas.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.darkonnen.dojosandninjas.models.Dojo;
import com.darkonnen.dojosandninjas.services.DojoService;

@Controller
public class DojoController {

	private final DojoService dojoService;

	public DojoController(DojoService dojoService) {
		this.dojoService = dojoService;
	}

	// NEW

	@RequestMapping(value = "dojos/new", method = RequestMethod.GET)
	public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
		return "/dojos/new.jsp";
	}

	// CREATE

//	@RequestMapping(value = "dojos/new", method = RequestMethod.POST)
//	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
//		if (result.hasErrors()) {
//			return "/dojos/new.jsp";
//		} else {
//			dojoService.createDojo(dojo);
//
//			return "redirect:/ninjas/new";
//		}
//	}
	
	@RequestMapping(value = "dojos/new", method = RequestMethod.POST)
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
//		if(dojoService.findByName(dojo.getName()).size() >= 1) {
		if(dojoService.ifExists(dojo.getName())) {
			return "redirect:/dojos/createError";
		} else {
			dojoService.createDojo(dojo);
			return "redirect:/ninjas/new";
		}
	}
	
	@RequestMapping(value="dojos/createError")
	public String flashMessage(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", "Dojo name already exists.");
		return "redirect:/dojos/new";
	
	}

	// READ ONE

	@RequestMapping(value = "dojos/{id}", method = RequestMethod.GET)
	public String showDojo(@PathVariable("id") Long id, Model model) {
		Dojo dojo = dojoService.findDojo(id);
		model.addAttribute("dojo", dojo);

		return "dojos/show.jsp";
	}

}
