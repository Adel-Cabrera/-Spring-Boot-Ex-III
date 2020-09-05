package com.darkonnen.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darkonnen.events.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
//	User findByApodo(String apodo);

}
