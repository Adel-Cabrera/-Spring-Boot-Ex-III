package com.darkonnen.coursestest.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.darkonnen.coursestest.entities.Course;
import com.darkonnen.coursestest.services.CourseService;

@Component
public class CourseValidator implements Validator{
	
	@Autowired
	private CourseService courseService;
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Course.class.equals(clazz);
    }
    
    @Override
    public void validate(Object object, Errors errors) {
    	Course course = (Course) object;         
    }


}
