package com.darkonnen.coursestest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkonnen.coursestest.entities.Course;
import com.darkonnen.coursestest.repositories.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	// READ ONE

	public Course findCourseById(Long id) {
		Optional<Course> optionalCourse = courseRepository.findById(id);
		if (optionalCourse.isPresent()) {
			return optionalCourse.get();
		} else {
			return null;
		}

	}

	// READ ALL // ASC QUERY

//	public List<Course> findAllCourses(){
//		return courseRepository.findAll();
//	}

	public List<Course> findAllCoursesDesc() {
		return courseRepository.findAllCoursesDesc();
	}

	// READ ALL // DESC QUERY

	public List<Course> findAllCoursesAsc() {
		return courseRepository.findAllCoursesAsc();
	}

	// CREATE - SAVE

	public void saveCourse(Course course) {
		courseRepository.save(course);
	}

	// CREATE - UPDATE

	public void updateCourse(Course course) {
		courseRepository.save(course);
	}

	// DELETE - DESTROY

	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}

}
