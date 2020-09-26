package com.darkonnen.students.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.students.models.User;

//@NoRepositoryBean
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAll();

    User findByUsername(String username);
	
	User findByEmail(String email);

	
}
