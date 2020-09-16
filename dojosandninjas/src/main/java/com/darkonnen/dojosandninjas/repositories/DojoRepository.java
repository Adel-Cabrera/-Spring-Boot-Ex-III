package com.darkonnen.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.dojosandninjas.models.Dojo;

@Repository
public interface DojoRepository extends CrudRepository<Dojo, Long> {
	
	List<Dojo> findAll(); // List <T> findAll(); SELECT * FROM DOJO.dojos
	
	boolean existsDojoByName(String name); // Spring Data JPA Query Creation; Creación automática de la Query.
	
//    List<Dojo> findByName(String name);
	
	// Exposición de métodos CRUD

	
}