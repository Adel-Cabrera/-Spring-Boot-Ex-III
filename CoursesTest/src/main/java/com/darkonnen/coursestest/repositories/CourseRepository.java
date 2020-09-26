package com.darkonnen.coursestest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.coursestest.entities.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
	
//	List<Course> findAll();
	
	@Query(value="SELECT * FROM courses ORDER BY course_capacity DESC", nativeQuery=true)
	List<Course> findAllCoursesDesc();
	
	@Query(value="SELECT * FROM courses ORDER BY course_capacity ASC", nativeQuery=true)
	List<Course> findAllCoursesAsc();
	
//	Course findCourseById(Long id);

}
