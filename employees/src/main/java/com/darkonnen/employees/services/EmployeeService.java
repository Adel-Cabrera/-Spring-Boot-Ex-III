package com.darkonnen.employees.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.employees.models.Employee;
import com.darkonnen.employees.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	private EmployeeRepository employeeRepo;
	
	public EmployeeService(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	public List<Employee> allEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee findEmployee(Long id) {
		Optional<Employee> optionalEmployee = employeeRepo.findById(id);
		if(optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		} else {
			return null;
		}
	}
	
	public Employee saveEmployee(Employee e) {
		return employeeRepo.save(e);
	}
}