package com.darkonnen.todo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkonnen.todo.models.Task;
import com.darkonnen.todo.repositories.TaskRepository;

@Service
public class TaskService {
	
//	@Autowired
	private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	// READ ALL
	
	public List<Task> findAllTasks() {
		return taskRepository.findAll();
		
	}
	
	// READ ONE
	
	public Task findById(Long id) {
		return taskRepository.findById(id).get();
	}
	
	// SAVE
	
	public void save(Task t) {
		taskRepository.save(t);
	}
	
	// DELETE
	
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}
	
}
