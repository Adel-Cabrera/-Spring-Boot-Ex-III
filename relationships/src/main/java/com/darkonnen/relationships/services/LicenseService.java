package com.darkonnen.relationships.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkonnen.relationships.models.License;
import com.darkonnen.relationships.repositories.LicenseRepository;

@Service 
public class LicenseService {
	// <----- Attributes ----->
		// Dependency injection
		private final LicenseRepository licenseRepository;
		
		// <----- Constructors ----->
		public LicenseService(LicenseRepository licenseRepository) {
			this.licenseRepository = licenseRepository;
		}
		
		// <----- Methods ----->		
		// SELECT * FROM licenses
		public List<License> allLicenses() {
			return licenseRepository.findAll();
		}
		
		// ADD
		public License createLicense(License license) {
			return licenseRepository.save(license);
		}
}