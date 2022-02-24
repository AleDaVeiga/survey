package com.survey.surveyapi.service;

import java.util.List;
import java.util.Optional;

import com.survey.surveyapi.dto.PollDTO;
import com.survey.surveyapi.model.Poll;
import com.survey.surveyapi.security.SurveyUserDetails;

public interface PollsService {
	Poll create(PollDTO poll);
	
	Poll create(PollDTO poll, SurveyUserDetails user);

	Poll update(PollDTO poll);

	void remove(Long id);

	List<Poll> findAll();

	List<Poll> findAll(SurveyUserDetails user);

	Optional<Poll> findById(Long id);
}
