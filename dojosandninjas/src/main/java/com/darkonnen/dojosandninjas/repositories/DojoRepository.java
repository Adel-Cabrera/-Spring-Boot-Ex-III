package com.darkonnen.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.dojosandninjas.models.Dojo;

@Repository
public interface DojoRepository extends CrudRepository<Dojo, Long> {
	List<Dojo> findAll();
	
	boolean existsDojoByName(String name);

//    List<Dojo> findByName(String name);
	
}