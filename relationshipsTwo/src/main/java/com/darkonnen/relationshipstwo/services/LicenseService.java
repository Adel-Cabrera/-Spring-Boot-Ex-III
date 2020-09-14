package com.darkonnen.relationshipstwo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.relationshipstwo.models.License;
import com.darkonnen.relationshipstwo.models.Person;
import com.darkonnen.relationshipstwo.repositories.LicenseRepository;

@Service
public class LicenseService {
	
	private final LicenseRepository licenseRepository;
	
	public LicenseService(LicenseRepository licenseRepository) {
		this.licenseRepository = licenseRepository;
	}
	
	public List<License> allLicenses(){
		return licenseRepository.findAll();
	}
	
	public License createLicense(License license) {
		return licenseRepository.save(license);
	}
	
	public License findLicense(Long id) {
		Optional<License> optionalLicense = licenseRepository.findById(id);
		if (optionalLicense.isPresent()) {
			return optionalLicense.get();
		} else {
			return null;
		}
	}

	public License updateLicense(Long id, String number, Date expirationDate, String state,
			Person person) {
		Optional<License> optionalLicense = licenseRepository.findById(id);
		if (optionalLicense.isPresent()) {
			License updateLicense = optionalLicense.get();

			// Update Fields
			updateLicense.setNumber(number);
			updateLicense.setExpirationDate(expirationDate);
			updateLicense.setState(state);
			updateLicense.setPerson(person);

			return licenseRepository.save(updateLicense);
		} else {
			return null;
		}
	}

	public void deleteLicense(Long id) {
		Optional<License> optionalLicense = licenseRepository.findById(id);
		if (optionalLicense.isPresent()) {
			licenseRepository.deleteById(id);
		}
	}

	

}
