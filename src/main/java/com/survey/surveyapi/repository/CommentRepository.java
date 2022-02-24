package com.survey.surveyapi.repository;

import java.util.List;

import com.survey.surveyapi.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPoll_Id(Long pollId);
	
	List<Comment> findByPoll_IdAndUser_Id(Long pollId, Long userId);
}
