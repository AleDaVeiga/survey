package com.survey.surveyapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserValidateDTO {
	@NotBlank
	@Size(min = 3, max = 50)
	private String login;

	@NotBlank
	@Size(min = 3, max = 50)
	private String email;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
