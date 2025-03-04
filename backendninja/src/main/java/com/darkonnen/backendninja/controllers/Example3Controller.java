package com.darkonnen.backendninja.controllers;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.darkonnen.backendninja.models.Person;

@Controller
@RequestMapping("/example3")
public class Example3Controller {
	
	public static final String FORM_VIEW = "form";
	public static final String RESULT_VIEW = "result";
	private static final Log LOGGER = LogFactory.getLog(Example3Controller.class);
	
	// Redirección forma 1
//	@GetMapping("/")
//	public String redirect() {
//		return "redirect:/example3/showform";
//	}
	
	// Redirección forma 2
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/example3/showform");
	}	
	
	@GetMapping("/showform") // NEW 
	public String showForm(Model model) {
		model.addAttribute("person", new Person());
//		int i = 6/0; -> Error 500
//		LOGGER.info("INFO TRACE");
//		LOGGER.warn("WARN TRACE");
//		LOGGER.error("ERROR TRACE");
//		LOGGER.debug("DEBBUG");
		return FORM_VIEW;
	}
	
	@PostMapping("/addperson") // CREATE
	public ModelAndView addPerson(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) {

		ModelAndView mav = new ModelAndView(); // LA VISTA (TEMPLATE) QUE REGRESA
		if(bindingResult.hasErrors()) {
			mav.setViewName(FORM_VIEW);
			
		} else {
			mav.setViewName(RESULT_VIEW);
			mav.addObject("person", person);
			
		}
			
			
//		System.out.println(person.getName());
		LOGGER.info("METHOD: 'addperson' -- PARAMS: '" + person + "'");
		
		return mav;
	} 


}
