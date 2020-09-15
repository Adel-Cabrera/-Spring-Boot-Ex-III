package com.darkonnen.tareas.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tareas")
public class Tarea {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) // +1 a cada ID automáticamente
	private Long id;
	
	private String nombreTarea;

	private String descripcionTarea; // Agregar validaciones
	
	public Tarea() { // Create (NEW - SAVE) Read Update Destroy - Delete  
		
	}

	public Tarea(Long id, String nombreTarea, String descripcionTarea) { // SAVE
		this.id = id;
		this.nombreTarea = nombreTarea;
		this.descripcionTarea = descripcionTarea;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	public String getDescripcionTarea() {
		return descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}
	
	

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", nombreTarea=" + nombreTarea + ", descripcionTarea=" + descripcionTarea + "]";
	}
	
	
	
	
}
