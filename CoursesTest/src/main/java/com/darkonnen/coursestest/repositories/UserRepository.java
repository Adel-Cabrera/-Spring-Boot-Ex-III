package com.darkonnen.coursestest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.darkonnen.coursestest.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAll();
	
	User findByUsername(String username);
	
	User findUserByEmail(String email);
	
}
