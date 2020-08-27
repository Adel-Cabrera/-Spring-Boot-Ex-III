package com.darkonnen.dojooverflow.models;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity 
@Table(name="questions") 
public class Question {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String question;
	@Column(updatable=false) 
	private Date createdAt;
	private Date updatedAt;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="question", fetch = FetchType.LAZY) 
	private List<Answer> answers;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
	@JoinTable(
		name = "questions_tags", 
		joinColumns = @JoinColumn(name = "question_id"), 
		inverseJoinColumns = @JoinColumn(name = "tag_id") 
	)
	private List<Tag> tags;

	public Question() {
	}

	public Question(String question) {
		this.question = question;
	}
	
	public Question(String question, List<Tag> tags) {
		this.question = question;
		this.tags = tags;
	}	

	public Question(String question, List<Tag> tags, List<Answer> answers) {
		this.question = question;
		this.tags = tags;
		this.answers = answers;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
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