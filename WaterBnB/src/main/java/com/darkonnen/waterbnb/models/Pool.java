package com.darkonnen.waterbnb.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="pools")
public class Pool {

	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=1)
	private String address;
	
	@Size(min=1)
	private String description;
	
	private Float cost;
	
	@Size(min=1)
	private String size;
	
	private double average_reviews;
	
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User host;
	
	@OneToMany(mappedBy="pool", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Review> reviews;
	
	public Pool() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public double getAverage_reviews() {
		return average_reviews;
	}

	public void setAverage_reviews() {
		double total = 0.0;
		for (int i = 0; i < this.reviews.size(); i++) {
			total += this.reviews.get(i).getRating();
		}
		double average = total/this.reviews.size();
		this.average_reviews = average;
	}

	@PrePersist
    protected void onCreate(){
    		this.setCreatedAt(new Date());
    }

    @PreUpdate
    protected void onUpdate(){
    		this.setUpdatedAt(new Date());
    }
}