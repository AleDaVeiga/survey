package com.survey.surveyapi.service;

import java.util.List;
import java.util.Optional;

import com.survey.surveyapi.dto.UserAdminDTO;
import com.survey.surveyapi.model.User;

public interface UsersAdminService {
	User create(UserAdminDTO user);

	User update(Long id, UserAdminDTO user);
	
	User validate(UserAdminDTO user, String email);

	List<User> findAll();

	Optional<User> findById(Long id);
}
