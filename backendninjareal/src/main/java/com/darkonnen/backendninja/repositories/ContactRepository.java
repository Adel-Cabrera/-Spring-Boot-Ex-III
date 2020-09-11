package com.darkonnen.backendninja.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.backendninja.entities.Contact;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Serializable>{
	
	public abstract Contact findById(int id);
	
	
}
