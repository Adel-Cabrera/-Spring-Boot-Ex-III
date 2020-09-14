package com.darkonnen.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.todo.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	List<Task> findAll();

}

// EXPONE MÉTODOS CRUD
