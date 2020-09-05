package com.darkonnen.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.darkonnen.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	// define campos para entitymanager
	private EntityManager entityManager;
	
	
	// inyección
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}
	

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results
		
		return employees;
	}

}
