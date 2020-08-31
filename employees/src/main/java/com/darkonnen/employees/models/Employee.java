package com.darkonnen.employees.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name="employees")
public class Employee {
	@Id 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String first_name;
	private String last_name;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@OneToMany(mappedBy="manager", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Employee> employees;	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="manager_id")
	private Employee manager;

	public Employee() {
	}
	
	public Employee(String first_name, String last_name) {
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public Employee(String first_name, String last_name, Employee manager) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.manager = manager;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Long getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	@PrePersist 
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate 
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}