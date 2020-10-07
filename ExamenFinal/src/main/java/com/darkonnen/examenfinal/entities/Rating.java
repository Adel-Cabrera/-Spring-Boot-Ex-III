package com.darkonnen.examenfinal.entities;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ratings")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Max(5)
	@Min(1)
	private int rating;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	public Rating() {

	}

	// RELACIONES

	// 1:N USERS
	@ManyToOne(fetch = FetchType.LAZY) //, cascade=CascadeType.ALL
	@JoinColumn(name="user_id")
	private User user;


	// 1:N SHOWS

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="show_id")
	private Show show;
	
	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}
	

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

//	@Override
//	public String toString() {
//		return "Rating [id=" + id + ", rating=" + rating + ", user=" + user + ", show=" + show + "]";
//	}

	
	

}
