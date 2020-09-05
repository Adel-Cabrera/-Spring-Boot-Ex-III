package com.darkonnen.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkonnen.cruddemo.dao.EmployeeDAO;
import com.darkonnen.cruddemo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeDAO employeeDAO;
	// inject employee DAO
	
	@Autowired
	public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
		this.employeeDAO = theEmployeeDAO;
	}
	
	// expose employee endpoint
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeDAO.findAll();
	}

}
