package com.darkonnen.tareas.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.darkonnen.tareas.models.Tarea;
import com.darkonnen.tareas.services.TareaService;

@Controller
public class TareaController {
	
	private final TareaService tareaService;
	
	public TareaController(TareaService tareaService) {
		this.tareaService = tareaService;
	}
	
	// C R U D -> Funciones básicas de la capa de modelamiento de datos.
	
	// READ ALL
	
	@GetMapping("")
	public ModelAndView index() { // Inversion of Control -> generar una instancia -> un objeto de la entidad
		ModelAndView mav = new ModelAndView("index");
		// QUÉ QUEREMOS MOSTRAR EN NUESTRO INDEX??
		// Queremos listar todas nuestras tareas
		List<Tarea> tareas = tareaService.findAllTareas();
		mav.addObject("tareas", tareas);		
		return mav;
	}
	
	
	// READ ONE -> /tareas/{id} -> @PathVariable
	@GetMapping(value="/{id}") // localhost:8080/1
	public ModelAndView mostrarTarea(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("mostrar_tarea"); // -> Nombre de la plantilla / template
		Tarea miTarea = tareaService.findById(id);
		mav.addObject("tarea", miTarea);
		return mav;
	}
	
	
	// CREATE -> NEW -> Objecto con constructor vacío
	
	@RequestMapping("/new") // -> de NEW le pasamos el objeto vacío "tarea" ppara que llene los campos y los envíe a /save
	public String nuevaTarea(Model model) {
		// CONSTRUCTOR VACÍO
		Tarea tarea = new Tarea();
		model.addAttribute("tarea", tarea);
		return "nueva_tarea";
	}
	
	// CREATE -> SAVE -> Constructor con campos para guardar en la BBDD
	
	@PostMapping(value="/save")
	public String guardarTarea(@ModelAttribute("tarea") Tarea tarea) {
		tareaService.saveTarea(tarea);
		return "redirect:/"; // -> Cuando usamos redirect, es para controllers, cuando no usamos es para vistas
		
	}
	
	
	// UPDATE -> EDIT
	
	@RequestMapping(value="/editar/{id}")
	public String editarTarea(@PathVariable("id") Long id, Model model) {
		Tarea tarea = tareaService.findById(id);
		model.addAttribute("tarea", tarea); // OBJETO PARA EDITAR
		return "editar_tarea";
	}
	
//    @RequestMapping(value="/editar/{id}", method=RequestMethod.PUT)
//    public String update(@Valid @ModelAttribute("book") Tarea tarea, BindingResult result) {
//        if (result.hasErrors()) {
//            return "redirect:/";
//        } else {
//        	tareaService.saveTarea(tarea);
//            return "redirect:/";
//        }
//    }
	
	
	
	// DESTROY - DELETE
	
	@RequestMapping(value="/borrar/{id}")
	public String borrarTarea(@PathVariable("id") Long id) {
		tareaService.deleteTarea(id);
		return "redirect:/";
	}

	
	
	

}
