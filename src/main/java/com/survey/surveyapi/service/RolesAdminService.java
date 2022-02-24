package com.survey.surveyapi.service;

import java.util.List;
import java.util.Optional;

import com.survey.surveyapi.model.Role;

public interface RolesAdminService {
	List<Role> findAll();

	Optional<Role> findById(Long id);
}
