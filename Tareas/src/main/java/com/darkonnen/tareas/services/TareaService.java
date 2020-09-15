package com.darkonnen.tareas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkonnen.tareas.models.Tarea;
import com.darkonnen.tareas.repositories.TareaRepository;

@Service
public class TareaService {
	
	private final TareaRepository tareaRepository;
	
	public TareaService(TareaRepository tareaRepository) {
		this.tareaRepository = tareaRepository;
	}
	
	// READ ALL
	
	public List<Tarea> findAllTareas(){
		return tareaRepository.findAll();
	}
	
	// READ ONE
	
	public Tarea findById(Long id) { // id=7
		return tareaRepository.findById(id).get();
	}
	
	// SAVE
	
	public void saveTarea(Tarea t) { // En save le pasamos el objeto
		tareaRepository.save(t);
	}
	
	// DESTROY - DELETE
	
	public void deleteTarea(Long id) { // En delete le pasamos el ID.
		tareaRepository.deleteById(id);
	}

}
