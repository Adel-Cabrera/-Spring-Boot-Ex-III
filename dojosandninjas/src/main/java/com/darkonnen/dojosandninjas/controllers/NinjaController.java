package com.darkonnen.dojosandninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.darkonnen.dojosandninjas.models.Dojo;
import com.darkonnen.dojosandninjas.models.Ninja;
import com.darkonnen.dojosandninjas.services.DojoService;
import com.darkonnen.dojosandninjas.services.NinjaService;

@Controller

public class NinjaController {
	
	private final NinjaService ninjaService;
	private final DojoService dojoService;
	
	
	public NinjaController(DojoService dojoService, NinjaService ninjaService) {
		this.dojoService = dojoService;
		this.ninjaService = ninjaService;
	}

	
	@RequestMapping(value="ninjas/new", method=RequestMethod.GET)
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> dojos = dojoService.allDojos();
		model.addAttribute("dojos", dojos);
		
		return "/ninjas/new.jsp";
	}
	
	// PUEDEN HABER MÚLTIPLES FORMAS DE HACER LAS COSAS
	
	// Thymeleaf

	@RequestMapping(value="ninjas/new", method=RequestMethod.POST)
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Dojo> dojos = dojoService.allDojos();
			model.addAttribute("dojos", dojos);
			return "/ninjas/new.jsp";
		} else {
			ninjaService.createNinja(ninja);
			Long dojoNum = ninja.getDojo().getId();
			
			return "redirect:/dojos/" + dojoNum;
		}
	}

}
