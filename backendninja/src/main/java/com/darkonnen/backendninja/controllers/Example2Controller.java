package com.darkonnen.backendninja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/example2")
public class Example2Controller {
	
	private static final String EXAMPLE2_VIEW = "example2"; // NOMBRE DE LA PLANTILLA HTML
	
//	localhost:8080/example2/request1?nombre=Darko
//	localhost:8080/example2/request1?nombre=Adel
	
	
	// GET FORMA 1 -> QUERY PARAMS
	@GetMapping("/request1")
	public ModelAndView request1(@RequestParam(name="nombre", required=false,defaultValue = "Human") String nombre) {
		ModelAndView mav = new ModelAndView(EXAMPLE2_VIEW);
		mav.addObject("nombre_en_modelo", nombre);
		return mav;
	}
	
	
	//	localhost:8080/example2/request1/Darko
	//	localhost:8080/example2/request1/Adel
	

	// GET FORMA 2 -> VARIABLE PARAMS
	
	@GetMapping("/request2/{nombre}")
	public ModelAndView request2(@PathVariable("nombre") String nombre) {
		ModelAndView mav = new ModelAndView(EXAMPLE2_VIEW);
		mav.addObject("nombre_en_modelo", nombre);
		return mav;
	}
	

}
