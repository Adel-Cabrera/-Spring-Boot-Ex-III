package com.darkonnen.coursestest.entities;

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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotEmpty
	@Size(min = 6,message="Username must be greater than 6 characters")
	private String courseName;

//	@NotEmpty
	@Size(min = 6)
	private String instructorName;

//	@Min(1)
	private int courseCapacity;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	public Course() {

	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creatorCourse;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_courses", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> enroledUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public int getCourseCapacity() {
		return courseCapacity;
	}

	public void setCourseCapacity(int courseCapacity) {
		this.courseCapacity = courseCapacity;
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

	public User getCreatorCourse() {
		return creatorCourse;
	}

	public void setCreatorCourse(User creatorCourse) {
		this.creatorCourse = creatorCourse;
	}

	public List<User> getEnroledUsers() {
		return enroledUsers;
	}

	public void setEnroledUsers(List<User> enroledUsers) {
		this.enroledUsers = enroledUsers;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", instructorName=" + instructorName
				+ ", courseCapacity=" + courseCapacity + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", creatorCourse=" + creatorCourse + ", enroledUsers=" + enroledUsers + "]";
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
