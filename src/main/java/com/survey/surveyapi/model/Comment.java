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
@Table(name = "comments")
public class Comment extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 255)
	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "poll_id")
	private Poll poll;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
