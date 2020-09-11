package com.darkonnen.backendninja.services.implementations;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.darkonnen.backendninja.entities.Course;
import com.darkonnen.backendninja.repositories.CourseJpaRepository;
import com.darkonnen.backendninja.services.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {
	
	private static final Log LOG = LogFactory.getLog(CourseServiceImpl.class);	
	
	@Autowired
	@Qualifier("courseJpaRepository")
	public CourseJpaRepository courseJpaRepository;
	

	@Override
	public Course addCourse(Course course) {
		LOG.info("Call: addCourse()");
		return courseJpaRepository.save(course);
	}

	@Override
	public List<Course> listAllCourses() {
		LOG.info("Call: listAllCourses()");
		return courseJpaRepository.findAll();
		 
	}

	@Override
	public Course updateCourse(Course course) {
		return courseJpaRepository.save(course);
	}

	@Override
	public int removeCourse(int id) {
		courseJpaRepository.deleteById(id);
		return 0;
	}

}
