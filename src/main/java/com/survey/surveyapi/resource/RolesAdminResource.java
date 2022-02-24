package com.survey.surveyapi.resource;

import java.util.List;

import com.survey.surveyapi.exception.NotFoundException;
import com.survey.surveyapi.model.Role;
import com.survey.surveyapi.service.RolesAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/roles")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RolesAdminResource {
	@Autowired
	private RolesAdminService rolesService;

	public RolesAdminResource(RolesAdminService rolesService) {
		this.rolesService = rolesService;
	}

	@GetMapping
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return rolesService.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public Role findById(@PathVariable(value = "id") Long id) {
		return rolesService.findById(id).orElseThrow(() -> new NotFoundException("Role", "id", id));
	}
}
