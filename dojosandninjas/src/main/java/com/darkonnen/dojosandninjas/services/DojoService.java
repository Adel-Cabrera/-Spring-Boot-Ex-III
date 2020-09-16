package com.darkonnen.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.dojosandninjas.models.Dojo;
import com.darkonnen.dojosandninjas.repositories.DojoRepository;

@Service
public class DojoService {

	
//	@Autowired
	private final DojoRepository dojoRepository;

	// Inyección de dependencias
	public DojoService(DojoRepository dojoRepository) {
		this.dojoRepository = dojoRepository;
	}

	
	// OBTENER TODOS
	public List<Dojo> allDojos() {
		return dojoRepository.findAll();
	}

	// OBTENER SOLO 1
	public Dojo findDojo(Long id) {
		Optional<Dojo> optionalDojo = dojoRepository.findById(id);
		if (optionalDojo.isPresent()) {
			return optionalDojo.get();
		} else {
			return null;
		}
	}
	
	// CREATE

	public Dojo createDojo(Dojo d) {
		return dojoRepository.save(d);
	}
	
//	public List<Dojo> findByName(String name) {
//		return dojoRepository.findByName(name);
//	}
	
		
	public boolean ifExists(String name) {
		return dojoRepository.existsDojoByName(name);
	}
	


}
