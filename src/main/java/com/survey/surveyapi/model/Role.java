package com.survey.surveyapi.model;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.survey.surveyapi.model.base.BaseEntity;
import com.survey.surveyapi.model.base.Roles;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Enumerated(STRING)
	@NaturalId
	@NotBlank
	@Size(max = 60)
	@Column(name = "name")
	private Roles name;

	public Roles getName() {
		return name;
	}

	public void setName(Roles name) {
		this.name = name;
	}
}
