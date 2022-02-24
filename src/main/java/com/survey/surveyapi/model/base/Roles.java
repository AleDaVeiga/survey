package com.survey.surveyapi.model.base;

public enum Roles {
	ROLE_ADMIN("admin"), //
	ROLE_MANAGE("manage"), //
	ROLE_PRODUCE("produce");
	
	private String description;

	private Roles(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
