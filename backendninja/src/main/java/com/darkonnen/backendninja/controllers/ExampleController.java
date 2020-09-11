package com.darkonnen.backendninja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.darkonnen.backendninja.components.ExampleComponent;
import com.darkonnen.backendninja.models.Person;
import com.darkonnen.backendninja.services.implementations.ExampleServiceImpl;

@Controller
@RequestMapping("/example")
public class ExampleController {
	
	public static final String EXAMPLE_VIEW = "example"; // NOMBRE DE LA PLANTILLA HTML
	
	@Autowired
	@Qualifier("exampleService")
	private ExampleServiceImpl exampleServiceImpl;
//	private ExampleService exampleService; -> FUNCIONA TAMBIÉN PORQUE ES LA CLASE PADRE

	
	@Autowired
	@Qualifier("exampleComponent")
	private ExampleComponent exampleComponent;
	
	// Primera forma
	@GetMapping("/exampleString")
//	@RequestMapping(value="/exampleString", method=RequestMethod.GET)
	public String exampleString(Model model) {
		
		exampleComponent.sayHello();
		
		Person person = new Person("Darko", 13);
		
		model.addAttribute("name", "Darko");
		model.addAttribute("person", person);
		model.addAttribute("people", exampleServiceImpl.getListPeople());

		return EXAMPLE_VIEW;
	}
	
	// Segunda forma
	@RequestMapping(value="/exampleMAV", method=RequestMethod.GET) // --> Get
	public ModelAndView exampleMAV() {
		
		Person person = new Person("Darkonnen", 33);
		ModelAndView mav = new ModelAndView(EXAMPLE_VIEW);
		mav.addObject("name", "Darkonnen");		
		mav.addObject("person", person);
		mav.addObject("people", exampleServiceImpl.getListPeople());
		return mav;

//		return new ModelAndView(EXAMPLE_VIEW); --> ModelAndView sin datos de objeto
	}
	

}
