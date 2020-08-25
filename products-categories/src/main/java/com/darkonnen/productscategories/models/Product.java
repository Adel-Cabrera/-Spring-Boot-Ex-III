package com.darkonnen.productscategories.models;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity 
@Table(name="products") 
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min=2, max=20, message="Name must be between 2 and 20 characters!") 
	private String name;
	@Size(min=10, max=100, message="Description must be between 10 and 100 characters!") 
	private String description;
	@DecimalMin(value="0.01", message="The minimum price must be above 0.00") 
	@Digits(integer=6, fraction=2, message="Please use the correct format!") 
	private Float price;
	@Column(updatable = false) 
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToMany(fetch = FetchType.LAZY) 
	@JoinTable(
			name = "categories_products", 
			joinColumns = @JoinColumn(name = "product_id"),  
			inverseJoinColumns = @JoinColumn(name = "category_id") 
	)
	private List<Category> categories;

	public Product() {
	}

	public Product(String name,	String description, Float price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Product(String name, String description, Float price, List<Category> categories) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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