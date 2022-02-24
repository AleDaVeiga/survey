package com.survey.surveyapi.resource;

import java.util.List;

import javax.validation.Valid;

import com.survey.surveyapi.dto.UserAdminDTO;
import com.survey.surveyapi.exception.NotFoundException;
import com.survey.surveyapi.model.User;
import com.survey.surveyapi.service.UsersAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UsersAdminResource {
	@Autowired
	private UsersAdminService usersService;

	public UsersAdminResource(UsersAdminService usersService) {
		this.usersService = usersService;
	}

	@PostMapping
	@ResponseBody
	@Transactional
	public User create(@RequestBody UserAdminDTO user) {
		return usersService.create(user);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Transactional
	public User update(@PathVariable(value = "id") Long id, @RequestBody @Valid UserAdminDTO user) {
		return usersService.update(id, user);
	}

	@GetMapping
	@ResponseBody
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return usersService.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public User findById(@PathVariable(value = "id") Long id) {
		return usersService.findById(id).orElseThrow(() -> new NotFoundException("User", "id", id));
	}
}
