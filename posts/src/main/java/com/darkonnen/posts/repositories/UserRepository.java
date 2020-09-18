package com.darkonnen.posts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.posts.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findAll();
	
	User findByEmail(String email);
}
