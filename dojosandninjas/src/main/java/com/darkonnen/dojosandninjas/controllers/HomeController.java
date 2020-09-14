package com.darkonnen.dojosandninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.darkonnen.dojosandninjas.models.Dojo;
import com.darkonnen.dojosandninjas.models.Ninja;
import com.darkonnen.dojosandninjas.services.MasterService;

@Controller 
public class HomeController {
	private final MasterService masterService;
	
	public HomeController(MasterService masterService) {
		this.masterService = masterService;
	}
	
	@RequestMapping(value="dojos/new", method=RequestMethod.GET)
	public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
		return "/dojos/new.jsp";
	}
	
	@RequestMapping(value="dojos/new", method=RequestMethod.POST)
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) {
			return "/dojos/new.jsp";
		} else {
			masterService.createDojo(dojo);
			
			return "redirect:/ninjas/new";
		}
	}
	
	
	@RequestMapping(value="ninjas/new", method=RequestMethod.GET)
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> dojos = masterService.allDojos();
		model.addAttribute("dojos", dojos);
		
		return "/ninjas/new.jsp";
	}
	
	@RequestMapping(value="dojos/{id}", method=RequestMethod.GET)
	public String showDojo(@PathVariable("id") Long id, Model model) {
		Dojo dojo = masterService.findDojo(id);
		model.addAttribute("dojo", dojo);
		
		return "dojos/show.jsp";
	}
		
	
	@RequestMapping(value="ninjas/new", method=RequestMethod.POST)
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Dojo> dojos = masterService.allDojos();
			model.addAttribute("dojos", dojos);
			return "/ninjas/new.jsp";
		} else {
			masterService.createNinja(ninja);
			Long dojoNum = ninja.getDojo().getId();
			
			return "redirect:/dojos/" + dojoNum;
		}
	}
	
}