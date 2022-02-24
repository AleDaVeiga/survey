package com.survey.surveyapi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String login;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String token, String login, Collection<? extends GrantedAuthority> authorities) {
		this.token = token;
		this.login = login;
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}