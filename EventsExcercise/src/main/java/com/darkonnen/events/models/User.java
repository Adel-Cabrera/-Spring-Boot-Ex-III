package com.darkonnen.events.models;

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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, message="Event name must not be empty")	
	private String firstName;
	
	@Size(min=1, message="Event name must not be empty")	
	private String lastName;	


	@Pattern(regexp="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$", message="Invalid email pattern")
    private String email;
	
	@Size(min=1, message="Event location must not be empty")	
	private String location;

    @Size(min=5, message="Password must be greater than 2 characters")
	private String password;
    
    @Transient
    private String passwordConfirmation;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToMany(mappedBy="host", fetch=FetchType.LAZY)
    private List<Event> hostedEvents;
    
    @ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="events_users",joinColumns = @JoinColumn(name="event_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> joinedEvents;
    
    @OneToMany(mappedBy="author", fetch = FetchType.LAZY)
    private List<Message> comments;
    
    public User() {
    	
    }
    
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public List<Event> getHostedEvents() {
		return hostedEvents;
	}

	public void setHostedEvents(List<Event> hostedEvents) {
		this.hostedEvents = hostedEvents;
	}

	public List<User> getJoinedEvents() {
		return joinedEvents;
	}

	public void setJoinedEvents(List<User> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}

	public List<Message> getComments() {
		return comments;
	}

	public void setComments(List<Message> comments) {
		this.comments = comments;
	}
	
    
    
    
    
}
