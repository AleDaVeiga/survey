package com.survey.surveyapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.survey.surveyapi.model.Comment;
import com.survey.surveyapi.repository.CommentRepository;
import com.survey.surveyapi.repository.PollRepository;
import com.survey.surveyapi.repository.UserRepository;
import com.survey.surveyapi.security.SurveyUserDetails;
import com.survey.surveyapi.service.CommentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentsServiceImpl implements CommentsService {	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PollRepository pollRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Comment create(Long pollId, String description) {
		Comment comment = new Comment();
		comment.setPoll(pollRepository.findById(pollId).get());
		comment.setDescription(description);
		return save(comment);
	}
	
	@Transactional
	public Comment create(Long pollId, String description, SurveyUserDetails user) {
		Comment comment = new Comment();
		comment.setPoll(pollRepository.findById(pollId).get());
		comment.setDescription(description);
		comment.setUser(userRepository.findById(user.getId()).get());
		return save(comment);
	}

	@Transactional
	public void createRelationship(List<Long> commentIds, SurveyUserDetails user) {
		for (Long commentId : commentIds) {
			Comment comment = commentRepository.findById(commentId).get();
			comment.setUser(userRepository.findById(user.getId()).get());
			save(comment);
		}
	}

	@Transactional
	public Comment update(Long id, String description) {
		Comment comment = commentRepository.findById(id).get();
		comment.setDescription(description);
		return save(comment);
	}

	private Comment save(Comment contrato) {
		return commentRepository.save(contrato);
	}

	@Transactional
	public void remove(Long id) {
		if (commentRepository.existsById(id)) {
			commentRepository.deleteById(id);
		}
	}

	@Transactional(readOnly = true)
	public List<Comment> findAll(Long pollId) {
		return commentRepository.findByPoll_Id(pollId);
	}

	@Transactional(readOnly = true)
	public List<Comment> findAllByUser(Long pollId, SurveyUserDetails user) {
		return commentRepository.findByPoll_IdAndUser_Id(pollId, user.getId());
	}

	@Transactional(readOnly = true)
	public Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}
}
