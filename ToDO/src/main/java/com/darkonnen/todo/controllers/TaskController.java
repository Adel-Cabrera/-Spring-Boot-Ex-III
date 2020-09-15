package com.darkonnen.todo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.darkonnen.todo.models.Task;
import com.darkonnen.todo.services.TaskService;

@Controller
@RequestMapping("/todo")
public class TaskController {
	
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	// READ ALL -> http://localhost:8080/todo
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		List <Task> listTasks = taskService.findAllTasks();
		mav.addObject("listTasks", listTasks);
		return mav;
	}
	
//	@RequestMapping("/")
//	public String index(Model model) {
//		List <Task> listTasks = taskService.findAllTasks();
//		model.addAttribute("listTask", listTasks);
//		return "index";
//	}
	
	// READ ONE -> http://localhost:8080/todo/1
	 
	@GetMapping(value="/{id}")
	public ModelAndView show(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("show");
		Task oneTask = taskService.findById(id);
		mav.addObject("task", oneTask);
		return mav;
	}

	
	// SAVE - NEW
	
	@RequestMapping("/new") // /todo/new
	public String newTask(Model model) { // NEW -> MODEL
		Task task = new Task();
		model.addAttribute("task", task);
		return "new_task";
	}
	
	// SAVE - CREATE
	
//	@RequestMapping(value="/save", method=RequestMethod.POST)
	@PostMapping(value="/save")
	public String saveTask(@ModelAttribute("task") Task task) { // CREATE -> MODEL-ATTRIBUTE & POST
		taskService.save(task);
		return "redirect:/todo";
	}
	
	// UPDATE
	
	
	@RequestMapping(value="/edit/{id}")
	public String saveTask(@PathVariable("id") Long id, Model model) { // UPDATE -> PATHVARIABLE
		Task editTask = taskService.findById(id);
		model.addAttribute("editTask", editTask);
		return "edit_task";
	}

	
	// DELETE
	
	@RequestMapping(value="/delete/{id}")
	public String deleteTask(@PathVariable("id") Long id) { // DELETE -> PATHVARIABLE
		taskService.delete(id);
		return "redirect:/todo";
	}

}
