package com.darkonnen.students.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.darkonnen.students.models.Course;
import com.darkonnen.students.repositories.CourseRepository;


public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> findAllPost(){
		return courseRepository.findAll();
	}
	
	public Course findCourseById(Long id) {
		Optional<Course> optionalCourse = courseRepository.findById(id);
		if(optionalCourse.isPresent()) {
			return optionalCourse.get();
		} else {
			return null;
		}
	}

	public void createCourse(Course course) {
		courseRepository.save(course);
	} 
	
	public void updatePost(Course course) {
		courseRepository.save(course);
	}
	
	public void deletePost(Long id) {
		courseRepository.deleteById(id);
	}


}
