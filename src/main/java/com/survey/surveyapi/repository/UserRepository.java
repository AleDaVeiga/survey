package com.survey.surveyapi.repository;

import java.util.List;
import java.util.Optional;

import com.survey.surveyapi.model.User;
import com.survey.surveyapi.model.base.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByLogin(String login);

	Boolean existsByLogin(String login);
	
	List<User> findByRoles_Name(Roles name);
}
