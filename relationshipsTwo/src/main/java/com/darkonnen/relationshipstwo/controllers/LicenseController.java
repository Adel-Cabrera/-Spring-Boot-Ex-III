package com.darkonnen.relationshipstwo.controllers;

import org.springframework.stereotype.Controller;

import com.darkonnen.relationshipstwo.services.LicenseService;

@Controller
public class LicenseController {
	
	private final LicenseService licenseService;
	
	public LicenseController(LicenseService licenseService) {
		this.licenseService = licenseService;
	}
	
	// READ ALL
	
	// READ ONE - show
	
	// NEW
	
	// CREATE
	

}
