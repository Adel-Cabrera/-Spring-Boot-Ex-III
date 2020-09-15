package com.darkonnen.tareas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.tareas.models.Tarea;

@Repository
public interface TareaRepository extends CrudRepository<Tarea, Long>{

	// CrudRepository -> Expone métodos de CRUD
	
	// Spring Data JPA -> crea queries basadas en la firma del método.

	List<Tarea> findAll();	// .save() / findById() / delete() -> AUTOMÁTICOS
}

// TAREA 1 NOMBRE Y DESCRIPCION
// TAREA 2 NOMBRE Y DESCRIPCION