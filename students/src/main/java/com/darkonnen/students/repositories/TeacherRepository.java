package com.darkonnen.students.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.students.models.Teacher;
import com.darkonnen.students.models.User;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long>{
	
	List<Teacher> findAll();

	Teacher findByUsername(String username);
	
	Teacher findByEmail(String email);


}
