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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="shows")
public class Show {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, message="Title must be present")
	private String showTitle;
	
	@Size(min=1, message="Network must be present")
	private String showNetwork;
	
	private double average_rating;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	public Show() {
		
	}
	
	// RELACIONES
	
	// N:1 USER
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creatorShow;
	
//////	 1:N RATING
	@OneToMany(mappedBy="show", fetch=FetchType.LAZY)
	private List<Rating> ratings;
	
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "shows_ratings", 
//        joinColumns = @JoinColumn(name = "show_id"), 
//        inverseJoinColumns = @JoinColumn(name = "rating_id")
//    )
//    private List<Rating> ratings;
//	
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
    
	public double getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating() {
		double total = 0.0;
		for (int i = 0; i < this.ratings.size(); i++) {
			total += this.ratings.get(i).getRating();
		}
		double average = total/this.ratings.size();
		this.average_rating = average;
	}
	
		
	public void setAverage_rating(double average_rating) {
		this.average_rating = average_rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public String getShowNetwork() {
		return showNetwork;
	}

	public void setShowNetwork(String showNetwork) {
		this.showNetwork = showNetwork;
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

	public User getCreatorShow() {
		return creatorShow;
	}

	public void setCreatorShow(User creatorShow) {
		this.creatorShow = creatorShow;
	}
	

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

//	@Override
//	public String toString() {
//		return "Show [id=" + id + ", showTitle=" + showTitle + ", showNetwork=" + showNetwork + ", average_rating="
//				+ average_rating + ", creatorShow=" + creatorShow + ", ratings=" + ratings + "]";
//	}
	
	


	
	


}
