package com.darkonnen.dojooverflow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	// GET routes
	// root
	@RequestMapping(value="", method = RequestMethod.GET)
	public String index() {
		return "/home/index.jsp";
	}
}