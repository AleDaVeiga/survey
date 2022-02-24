package com.survey.surveyapi.repository;

import com.survey.surveyapi.model.EmailConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailConfigRepository extends JpaRepository<EmailConfig, Long> {
	EmailConfig findFirstByOrderByIdDesc();
}
