package com.darkonnen.examenfinal.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.examenfinal.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAll();
	
	User findByUsername(String username);
	
	User findUserByEmail(String email);

	
}
