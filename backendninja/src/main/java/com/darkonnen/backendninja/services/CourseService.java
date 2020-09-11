package com.darkonnen.backendninja.services;

import java.util.List;

import com.darkonnen.backendninja.entities.Course;

public interface CourseService {

	public abstract Course addCourse(Course course); // CREATE

	public abstract List<Course> listAllCourses(); // READ

	public abstract Course updateCourse(Course course); // UPDATE

	public abstract int removeCourse(int id); // DELETE

}
