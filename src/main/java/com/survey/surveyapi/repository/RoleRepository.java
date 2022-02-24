package com.survey.surveyapi.repository;

import java.util.Optional;

import com.survey.surveyapi.model.Role;
import com.survey.surveyapi.model.base.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
