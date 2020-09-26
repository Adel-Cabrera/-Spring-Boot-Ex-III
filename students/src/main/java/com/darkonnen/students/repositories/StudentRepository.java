package com.darkonnen.students.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.students.models.Student;
import com.darkonnen.students.models.Teacher;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	
	List<Student> findAll();

	Student findByUsername(String username);
	
	Student findByEmail(String email);


}
