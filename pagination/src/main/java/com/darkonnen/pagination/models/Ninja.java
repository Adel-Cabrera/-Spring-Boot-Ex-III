package com.darkonnen.pagination.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;


@Entity 
@Table(name="ninjas") public class Ninja {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="First name cannot be null!")
	@Size(min=2, max=20, message="First Name must be between 2 and 20 characters!")
	private String firstName;
	@NotNull(message="Last name cannot be null!")
	@Size(min=2, max=20, message="Last Name must be between 2 and 20 characters!")
	private String lastName;
	private Date createdAt;
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dojo_id")
	private Dojo dojo;
	
	public Ninja() {
	}

	public Ninja(String firstName, String lastName, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Ninja(String firstName, String lastName, Integer age, Dojo dojo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dojo = dojo;
	}
	
	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public Dojo getDojo() {
		return dojo;
	}

	public void setDojo(Dojo dojo) {
		this.dojo = dojo;
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
	
	@PrePersist 	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate 	protected void onUpdate() {
		this.updatedAt = new Date();
	}		
}