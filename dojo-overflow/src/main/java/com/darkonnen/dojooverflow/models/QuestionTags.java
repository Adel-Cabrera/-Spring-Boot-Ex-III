package com.darkonnen.dojooverflow.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class QuestionTags {
	@NotBlank(message="No puede estar en blanco.")
	private String question;
	@Pattern(regexp="^$|([a-z *]+(,[a-z *]+(,[a-z *])*)*){1}$", message="Los tags debe ser separados por comas y solo pueden haber 3 máximo.")
	private String tags;
	
	public QuestionTags() {
	}

	public QuestionTags(String question) {
		this.question = question;
	}

	public QuestionTags(String question, String tags) {
		this.question = question;
		this.tags = tags;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}	
	
}