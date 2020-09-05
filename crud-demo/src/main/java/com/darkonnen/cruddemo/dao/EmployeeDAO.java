package com.darkonnen.cruddemo.dao;

import java.util.List;

import com.darkonnen.cruddemo.entity.Employee;

public interface EmployeeDAO {
	public List<Employee> findAll();

}
