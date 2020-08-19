package com.darkonnen.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.relationships.models.Person;
import com.darkonnen.relationships.repositories.PersonRepository;

@Service // Sets class as service
public class PersonService {
	// <----- Attributes ----->
	// Dependency injection
	private final PersonRepository personRepository;
	
	// <----- Constructors ----->
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Person> allPersons() {
		return personRepository.findAll();
	}
	
	public Person createPerson(Person person) {
		return personRepository.save(person);
	}
	
	public Person findPerson(Long id) {
		Optional<Person> optionalP = personRepository.findById(id);
		if(optionalP.isPresent()) {
			return optionalP.get();
		} else {
			return null;
		}
	}
}