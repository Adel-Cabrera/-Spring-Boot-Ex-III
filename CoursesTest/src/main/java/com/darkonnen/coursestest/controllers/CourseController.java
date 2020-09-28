package com.darkonnen.coursestest.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.darkonnen.coursestest.entities.Course;
import com.darkonnen.coursestest.entities.Role;
import com.darkonnen.coursestest.entities.User;
import com.darkonnen.coursestest.services.CourseService;
import com.darkonnen.coursestest.services.UserService;
import com.darkonnen.coursestest.validators.CourseValidator;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseValidator courseValidator;

	// READ ALL

	@GetMapping("")
	public String homePage(@Valid @ModelAttribute("courses") Course courses, Principal principal, HttpSession session,
			Model model) {
		String email = principal.getName();
		User currentUser = userService.findUserByEmail(email);
//		List<Course> allCourses = courseService.findAllCoursesDesc();
		if ((Integer) session.getAttribute("order") == null || (Integer) session.getAttribute("order") == 0) {
			session.setAttribute("order", 0);
			model.addAttribute("allCourses", courseService.findAllCoursesDesc());
		} else {
			model.addAttribute("allCourses", courseService.findAllCoursesAsc());
		}
//		List<Course> currentUserPosts = currentUser.getUserCourses();
//		model.addAttribute("currentUserCourses", currentUserPosts);
//		model.addAttribute("allCourses", allCourses);

		model.addAttribute("currentUser", currentUser);
		List<Role> userRoles = currentUser.getRoles();
		model.addAttribute("userRoles", userRoles);

		return "home";
	}

	// READ ONE
	@GetMapping(value = "/{id}")
	public String showCourse(@PathVariable("id") Long id, Model model, Principal principal) {
		Course course = courseService.findCourseById(id);
		User user = course.getCreatorCourse();
		String email = principal.getName();
		User currentUser = userService.findUserByEmail(email);
		model.addAttribute("currentUser", currentUser);
		List<User> enroledUsers= course.getEnroledUsers();
		model.addAttribute("enroledUsers", enroledUsers);
		model.addAttribute("course", course);
		model.addAttribute("creatorCourse", user);
		return "show";
	}

	// NEW
	@GetMapping(value="/new")
	public String newCourse(Model model) {
		Course newCourse = new Course();
		model.addAttribute("newCourse", newCourse);
		
		return "new";
	}

	// CREATE
	@PostMapping(value="/create")
	public String createCourse(@ModelAttribute("newCourse") @Valid Course course, Principal principal, BindingResult result) {

		courseValidator.validate(course, result);
		
		if(result.hasErrors()) {
			return "redirect:/courses/new";	
		} else {
			String email = principal.getName();
			User creatorCourse = userService.findUserByEmail(email);
			course.setCreatorCourse(creatorCourse);
//			List<User> addCreator = new ArrayList<User>();
//			addCreator.add(creatorCourse);
//			course.setEnroledUsers(addCreator);
			courseService.saveCourse(course);
			return "redirect:/courses";	
		}
		
	}
	
	@GetMapping(value="/{id}/join")
	public String joinCourse(@PathVariable("id") Long id, Principal principal) {
		String email = principal.getName();
		User user = userService.findUserByEmail(email);
		Course course = courseService.findCourseById(id);
		List<User> enroledUsers = course.getEnroledUsers();
		if(enroledUsers.size() < course.getCourseCapacity()) {
			enroledUsers.remove(user); // MUY HACKER
			enroledUsers.add(user);
			course.setEnroledUsers(enroledUsers);
			courseService.updateCourse(course);
			return "redirect:/courses";

		}else {
			return "redirect:/courses";
		}
		
	}
	
	@GetMapping(value="/{id}/cancel")
	public String cancelCourse(@PathVariable("id") Long id, Principal principal) {
		String email = principal.getName();
		User user = userService.findUserByEmail(email);
		Course course = courseService.findCourseById(id);
		List<User> enroledUsers = course.getEnroledUsers();
		enroledUsers.remove(user);
		course.setEnroledUsers(enroledUsers);
		courseService.updateCourse(course);
//		return "redirect:/courses";
		return "redirect:/courses/" + course.getId();
	}


	// EDIT
	@GetMapping(value="/{id}/edit")
	public String editCourse(@PathVariable("id") Long id, Model model) {
		Course editCourse = courseService.findCourseById(id);
		model.addAttribute("editCourse", editCourse);
		return "edit";
	}

	// UPDATE
	
	@PostMapping(value="/{id}/edit")
	public String updateCourse(@PathVariable("id") Long id, @Valid @ModelAttribute("editCourse") Course editedCourse, Model model, Principal principal, BindingResult result) {
		String email = principal.getName();
		Course course = courseService.findCourseById(id);
		User creatorCourse = userService.findUserByEmail(email);
		model.addAttribute("course", course); // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx
		if(result.hasErrors()) {
			return "redirect:/courses/" + course.getId() + "/edit";
		}else {
			editedCourse.setCreatorCourse(creatorCourse);
			courseService.updateCourse(editedCourse);
			return "redirect:/courses/";
		}
		
	}

	// DELETE
	@GetMapping(value="/{id}/delete")
	public String deleteCourse(@PathVariable("id") Long id) {
		courseService.deleteCourse(id);
		return "redirect:/courses/";
	}
	
	// BLACK BELT
	
	@GetMapping(value="/desc")
	public String orderDesc(HttpSession session) {
		session.setAttribute("order", 0);
		return "redirect:/";
	}
	
	@GetMapping(value="/asc")
	public String orderAsc(HttpSession session) {
		session.setAttribute("order", 1);
		return "redirect:/";
	}


}
