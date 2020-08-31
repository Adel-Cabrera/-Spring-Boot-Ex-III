package com.darkonnen.pagination.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DojoController {
	private DojoService dojoService;
	
	public DojoController(DojoService dojoService) {
		this.dojoService = dojoService;
	}

	@RequestMapping("/pages/{pageNumber}")
	public String getDojosPerPage(Model model, @PathVariable("pageNumber") int pageNumber) {
	    Page<Object[]> dojos = dojoService.dojosPerPage(pageNumber - 1);
	    int totalPages = dojos.getTotalPages();
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("dojos", dojos);
	    return "home/index.jsp";
	}
}