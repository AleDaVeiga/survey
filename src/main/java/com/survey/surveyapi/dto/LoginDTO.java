package com.survey.surveyapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDTO {
	@NotBlank
	@Size(min = 3, max = 50)
	private String login;

	@NotBlank
	@Size(min = 4, max = 40)
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
