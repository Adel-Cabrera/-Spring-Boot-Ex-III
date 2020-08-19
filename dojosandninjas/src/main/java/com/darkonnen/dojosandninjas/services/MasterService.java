package com.darkonnen.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.dojosandninjas.models.Dojo;
import com.darkonnen.dojosandninjas.models.Ninja;
import com.darkonnen.dojosandninjas.repositories.DojoRepository;
import com.darkonnen.dojosandninjas.repositories.NinjaRepository;

@Service 
public class MasterService {
	private final DojoRepository dojoRepo;
	private final NinjaRepository ninjaRepo;
	
	public MasterService(DojoRepository dojoRepo, NinjaRepository ninjaRepo) {
		this.dojoRepo = dojoRepo;
		this.ninjaRepo = ninjaRepo;
	}
	
	public List<Dojo> allDojos() {
		return dojoRepo.findAll();
	}
	
	public List<Ninja> allNinjas() {
		return ninjaRepo.findAll();
	}
	
	public Dojo findDojo(Long id) {
		Optional<Dojo> oDojo = dojoRepo.findById(id);
		if(oDojo.isPresent()) {
			return oDojo.get();
		} else {
			return null;
		}
	}
	
	public Ninja findNinja(Long id) {
		Optional<Ninja> oNinja = ninjaRepo.findById(id);
		if(oNinja.isPresent()) {
			return oNinja.get();
		} else {
			return null;
		}
	}
	
	public Dojo createDojo(Dojo d) {
		return dojoRepo.save(d);
	}
	
	public Ninja createNinja(Ninja n) {
		return ninjaRepo.save(n);
	}
}