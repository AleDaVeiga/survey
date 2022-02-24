package com.survey.surveyapi.service;

import java.util.List;
import java.util.Optional;

import com.survey.surveyapi.model.Comment;
import com.survey.surveyapi.security.SurveyUserDetails;

public interface CommentsService {	
	Comment create(Long pollId, String description);
	
	Comment create(Long pollId, String description, SurveyUserDetails user);
	
	Comment update(Long id, String description);

	void remove(Long id);
	
	List<Comment> findAll(Long pollId);

	List<Comment> findAll(Long pollId, SurveyUserDetails user);

	Optional<Comment> findById(Long id);
}
