package com.darkonnen.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	@Size(min = 1, max = 64, message = "Password must be 8-16 characters in length")
	private String first;
	@Size(min = 1, max = 64)
	private String last;
	@Email(message = "Invalid email format. Ex: user@user.com")
	private String email;
	@Size(min = 1, max = 64)
	private String city;
	private String state;
	@Size(min = 8)
	private String password;
	@Transient
	@Size(min = 8)
	private String confirm;
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	//events
	@OneToMany(mappedBy = "hostess", fetch = FetchType.LAZY)
	private List<Event> hosting; // UN USUARIO PUEDE CREAR MUCHOS EVENTOS

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "attending", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	private List<Event> eventsJoined; // UN USUARIO PUEDE ASISTIR A MUCHOS EVENTOS

	//messages
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Message> messages; // 


	//constructors
	public User() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}


	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Event> getHosting() {
		return hosting;
	}

	public void setHosting(List<Event> hosting) {
		this.hosting = hosting;
	}

	public List<Event> getEventsJoined() {
		return eventsJoined;
	}

	public void setEventsJoined(List<Event> eventsJoined) {
		this.eventsJoined = eventsJoined;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
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