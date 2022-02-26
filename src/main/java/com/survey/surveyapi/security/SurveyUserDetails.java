package com.survey.surveyapi.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.survey.surveyapi.dto.BaseEntityDTO;
import com.survey.surveyapi.model.User;

public class SurveyUserDetails extends BaseEntityDTO implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String login;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public SurveyUserDetails(Long id, String login, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.authorities = authorities;
	}

	public static SurveyUserDetails build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new SurveyUserDetails(//
				user.getId(), //
				user.getLogin(), //
				user.getPassword(), //
				authorities);
	}

	public String getUsername() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || !(obj instanceof SurveyUserDetails)) {
			return false;
		}
		return getId().equals(((SurveyUserDetails) obj).getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}
