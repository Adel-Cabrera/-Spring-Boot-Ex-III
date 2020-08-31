package com.darkonnen.employees.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darkonnen.employees.models.Employee;
import com.darkonnen.employees.services.EmployeeService;

@RestController
@RequestMapping("/")
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value="managers", method=RequestMethod.GET, produces="application/json")
	public List<Employee> getEmployees(@RequestParam("id") Long id) {
		return employeeService.findEmployee(id).getEmployees();
	}
	
	@RequestMapping(value="employees", method=RequestMethod.GET, produces="application/json")
	public Employee getManager(@RequestParam("id") Long id) {
		return employeeService.findEmployee(id).getManager();
	}
	
	@RequestMapping(value="employees/new", method=RequestMethod.GET, produces="application/json")
	public Employee newEmployee(@RequestParam("firstname") String fName, @RequestParam("lastname") String lName) {
		return employeeService.saveEmployee(new Employee(fName, lName));
	}
	
	@RequestMapping(value="employees/update/{id}", method=RequestMethod.GET, produces="application/json")
	public Employee addManager(@PathVariable("id") Long eId, @RequestParam("managerid") Long mId) {
		Employee employee = employeeService.findEmployee(eId);
		employee.setManager(employeeService.findEmployee(mId));
		return employeeService.saveEmployee(employee);
	}
}