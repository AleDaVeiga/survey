package com.survey.surveyapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.survey.surveyapi.model.Role;
import com.survey.surveyapi.repository.RoleRepository;
import com.survey.surveyapi.service.RolesAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolesAdminServiceImpl implements RolesAdminService {
	@Autowired
	private RoleRepository roleRepository;

	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}
}
