package com.survey.surveyapi.service.impl;

import static com.survey.surveyapi.util.PasswordUtils.getValidRandomPassword;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.survey.surveyapi.dto.UserAdminDTO;
import com.survey.surveyapi.exception.NotFoundException;
import com.survey.surveyapi.mail.EmailBuilder;
import com.survey.surveyapi.mail.EmailDTO;
import com.survey.surveyapi.model.Role;
import com.survey.surveyapi.model.User;
import com.survey.surveyapi.model.base.Roles;
import com.survey.surveyapi.repository.RoleRepository;
import com.survey.surveyapi.repository.UserRepository;
import com.survey.surveyapi.service.EmailService;
import com.survey.surveyapi.service.UsersAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersAdminServiceImpl implements UsersAdminService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	@Transactional
	public User create(UserAdminDTO user) {
		String password = getValidRandomPassword();
		User newUser = new User();
		newUser.setPassword(encoder.encode(password));
		newUser.setLogin(user.getLogin());
		newUser.setRoles(attachRoles(user.getRoles(), newUser.getRoles()));
		return save(newUser);
	}
	
	private Set<Role> attachRoles(Set<String> rolesStr, Set<Role> roles) {
		for (Roles name : Roles.values()) {
			Role role = findRole(name);
			if (rolesStr.contains(name.toString())) {
				if (!roles.contains(role)) {
					roles.add(role);
				}
			} else {
				roles.remove(role);
			}
		}
		return roles;
	}

	private Role findRole(Roles role) {
		return roleRepository.findByName(role).orElseThrow(() -> new NotFoundException("Role", "name", role.getDescription()));
	}

	private User save(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User update(Long id, UserAdminDTO user) {
		User oldUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", "id", id));
		oldUser.setLogin(user.getLogin());
		oldUser.setRoles(attachRoles(user.getRoles(), oldUser.getRoles()));
		return save(oldUser);
	}
	
	@Transactional
	public User validate(UserAdminDTO userDTO, String email) {
		User user = userRepository.findByLogin(userDTO.getLogin()).orElseThrow(() -> new NotFoundException("User", "login", userDTO.getLogin()));
		sendValidateEmail(userDTO, email);
		return user;
	}

	private void sendValidateEmail(UserAdminDTO user, String email) {
		EmailDTO emailDTO = new EmailBuilder().to(asList(email)).subject("Validate email").content("user", user).content("email", email).html().build();
		emailService.sendHtml("validate-user", emailDTO);
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
}
