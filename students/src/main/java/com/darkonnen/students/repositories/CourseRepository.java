package com.darkonnen.students.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.students.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
	
	List<Course> findAll(); 
	
}
