package com.darkonnen.students.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3)
	private String username;

//	@Email
	@Size(min = 1)
	private String email;

	@Size(min = 5, message = "Password must be greater than 5 characters")
	private String password;

	@Transient
	private String passwordConfirmation;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@Size(min = 10)
	private String jobExperience;

	public Teacher() {
	}

	// RELACIONES

	// COURSES 1:n 
	@OneToMany(mappedBy = "courseTeacher", fetch = FetchType.LAZY)
	List<Course> teachingCourses;

	public List<Course> getTeachingCourses() {
		return teachingCourses;
	}

	public void setTeachingCourses(List<Course> teachingCourses) {
		this.teachingCourses = teachingCourses;
	}

	// ROLES 1:N

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teacher_roles", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> teacherRoles;

	
	public List<Role> getTeacherRoles() {
		return teacherRoles;
	}

	public void setTeacherRoles(List<Role> teacherRoles) {
		this.teacherRoles = teacherRoles;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getJobExperience() {
		return jobExperience;
	}

	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
