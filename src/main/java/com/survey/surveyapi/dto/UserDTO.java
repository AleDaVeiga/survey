package com.survey.surveyapi.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
	@NotBlank
	@Size(min = 3, max = 50)
	private String login;

	@NotBlank
	@Size(min = 4, max = 40)
	private String password;

	private Set<String> roles;

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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
