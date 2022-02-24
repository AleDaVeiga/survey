package com.survey.surveyapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.survey.surveyapi.model.base.BaseEntity;

@Entity
@Table(name = "polls")
public class Poll extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 255)
	@Column(name = "title")
	private String title;

	@NotBlank
	@Size(max = 255)
	@Column(name = "description")
	private String description;

	@NotBlank
	@Size(max = 255)
	@Column(name = "options")
	private String options;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
