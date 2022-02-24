package com.survey.surveyapi.repository;

import java.util.List;

import com.survey.surveyapi.model.Poll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
	List<Poll> findByUser_Id(Long userId);
}
