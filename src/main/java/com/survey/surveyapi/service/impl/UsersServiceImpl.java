package com.survey.surveyapi.service.impl;

import static com.survey.surveyapi.model.base.Roles.ROLE_ADMIN;
import static com.survey.surveyapi.model.base.Roles.ROLE_MANAGE;
import static com.survey.surveyapi.model.base.Roles.ROLE_PRODUCE;
import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.Set;

import com.survey.surveyapi.dto.ChangePasswordDTO;
import com.survey.surveyapi.dto.UserDTO;
import com.survey.surveyapi.dto.UserValidateDTO;
import com.survey.surveyapi.exception.NonUniqueException;
import com.survey.surveyapi.exception.NotFoundException;
import com.survey.surveyapi.mail.EmailBuilder;
import com.survey.surveyapi.mail.EmailDTO;
import com.survey.surveyapi.model.Role;
import com.survey.surveyapi.model.User;
import com.survey.surveyapi.model.base.Roles;
import com.survey.surveyapi.repository.RoleRepository;
import com.survey.surveyapi.repository.UserRepository;
import com.survey.surveyapi.service.EmailService;
import com.survey.surveyapi.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	@Transactional
	public User create(UserDTO user) {
		if (userRepository.existsByLogin(user.getLogin())) {
			throw new NonUniqueException("User", "login", user.getLogin());
		}
		return save(user);
	}

	private User save(UserDTO user) {
		User newUser = new User(user.getLogin(), encoder.encode(user.getPassword()));

		Set<String> rolesStr = user.getRoles();
		Set<Role> roles = new HashSet<>();

		rolesStr.forEach(role -> {
			switch (role) {
			case "admin":
				roles.add(findRole(ROLE_ADMIN));
				break;
			case "manage":
				roles.add(findRole(ROLE_MANAGE));
				break;
			default:
				roles.add(findRole(ROLE_PRODUCE));
			}
		});

		newUser.setRoles(roles);
		return userRepository.save(newUser);
	}

	private Role findRole(Roles role) {
		return roleRepository.findByName(role).orElseThrow(() -> new NotFoundException("Role", "name", role.getDescription()));
	}
	
	@Transactional
	public User update(UserDTO user) {
		User userToChange = userRepository.findByLogin(user.getLogin()).orElseThrow(() -> new NotFoundException("User", "login", user.getLogin()));
		if (userRepository.existsByLogin(user.getLogin())) {
			throw new NonUniqueException("User", "login", user.getLogin());
		}
		userToChange.setLogin(user.getLogin());
		return userRepository.save(userToChange);
	}

	@Transactional
	public User updatePassword(ChangePasswordDTO user) {
		User userToChange = userRepository.findByLogin(user.getLogin()).orElseThrow(() -> new NotFoundException("User", "login", user.getLogin()));
		userToChange.setPassword(encoder.encode(user.getNewPassword()));
		return userRepository.save(userToChange);
	}
	
	@Transactional
	public User validate(UserValidateDTO user) {
		User userToValidate = userRepository.findByLogin(user.getLogin()).orElseThrow(() -> new NotFoundException("User", "login", user.getLogin()));
		sendValidateEmail(userToValidate, user.getEmail());
		return userToValidate;
	}

	private void sendValidateEmail(User user, String email) {
		EmailDTO emailDTO = new EmailBuilder().to(asList(email)).subject("Validate email").content("user", user).content("email", email).html().build();
		emailService.sendHtml("validate-user", emailDTO);
	}
}
