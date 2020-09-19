package com.darkonnen.posts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.posts.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
    List<Role> findAll();
    
    List<Role> findByName(String name);
}
