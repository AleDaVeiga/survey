package com.survey.surveyapi.service.impl;

import com.survey.surveyapi.model.User;
import com.survey.surveyapi.repository.UserRepository;
import com.survey.surveyapi.security.SurveyUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found -> login: " + login));
		return SurveyUserDetails.build(user);
	}
}
