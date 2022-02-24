package com.survey.surveyapi.resource;

import javax.validation.Valid;

import com.survey.surveyapi.dto.ChangePasswordDTO;
import com.survey.surveyapi.dto.LoginDTO;
import com.survey.surveyapi.dto.UserDTO;
import com.survey.surveyapi.dto.UserValidateDTO;
import com.survey.surveyapi.model.User;
import com.survey.surveyapi.security.JwtProvider;
import com.survey.surveyapi.security.JwtResponse;
import com.survey.surveyapi.security.SurveyUserDetails;
import com.survey.surveyapi.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersResource {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UsersService usersService;

	@PostMapping("/login")
	public JwtResponse login(@Valid @RequestBody LoginDTO login) {
		UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
		Authentication authentication = authenticationManager.authenticate(userToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		SurveyUserDetails user = (SurveyUserDetails) authentication.getPrincipal();

		return new JwtResponse(jwt, user.getUsername(), user.getAuthorities());
	}

	@GetMapping("/roles")
	public SurveyUserDetails roles() {
		return (SurveyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@PostMapping
	public User create(@Valid @RequestBody UserDTO user) {
		return usersService.create(user);
	}

	@PutMapping
	@PreAuthorize("isAuthenticated()")
	public User update(@Valid @RequestBody UserDTO user) {
		return usersService.update(user);
	}

	@PutMapping("/password")
	@PreAuthorize("isAuthenticated()")
	public User updatePassword(@Valid @RequestBody ChangePasswordDTO user) {
		UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
		authenticationManager.authenticate(userToken);
		return usersService.updatePassword(user);
	}

	@PostMapping("/validate")
	public User restabelecer(@RequestBody UserValidateDTO user) {
		return usersService.validate(user);
	}
}
