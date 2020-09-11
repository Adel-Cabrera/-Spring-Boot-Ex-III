package com.darkonnen.backendninja.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.darkonnen.backendninja.entities.Course;
import com.darkonnen.backendninja.services.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	
	private static final String COURSE_VIEW = "courses";
	private static final Log LOG = LogFactory.getLog(CourseController.class);
	
	@Autowired
	@Qualifier("courseServiceImpl")
	private CourseService courseService; // IMPL
	
	@GetMapping("/listcourses")
	public ModelAndView listAllCourses() {
		LOG.info("Call: listAllCourses" );
		ModelAndView mav = new ModelAndView(COURSE_VIEW);
		mav.addObject("course", new Course()); // OBJETO VACÍO PARA THYMELEAF
		mav.addObject("courses", courseService.listAllCourses());
		return mav;
	}
	
	@PostMapping("/addcourse")
	public String addCourse(@ModelAttribute("course") Course course) {
		LOG.info("Call: addCourse() --PARAM: " + course.toString());
		courseService.addCourse(course);
		return "redirect:/courses/listcourses";
	}

}
