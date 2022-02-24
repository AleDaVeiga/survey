package com.survey.surveyapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.survey.surveyapi.dto.PollDTO;
import com.survey.surveyapi.model.Poll;
import com.survey.surveyapi.repository.PollRepository;
import com.survey.surveyapi.repository.UserRepository;
import com.survey.surveyapi.security.SurveyUserDetails;
import com.survey.surveyapi.service.PollsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollsServiceImpl implements PollsService {
	@Autowired
	private PollRepository pollRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public Poll create(PollDTO pollDTO) {
		return save(new Poll(), pollDTO);
	}

	private Poll save(Poll poll, PollDTO pollDTO) {
		poll.setTitle(pollDTO.getTitle());
		poll.setDescription(pollDTO.getDescription());
		poll.setOptions(pollDTO.getOptions());
		return pollRepository.save(poll);
	}
	
	@Transactional
	public Poll create(PollDTO pollDTO, SurveyUserDetails user) {
		Poll poll = new Poll();
		poll.setUser(userRepository.findById(user.getId()).get());
		return save(poll, pollDTO);
	}

	@Transactional
	public Poll update(PollDTO pollDTO) {
		return save(pollRepository.findById(pollDTO.getId()).get(), pollDTO);
	}

	@Transactional
	public void remove(Long id) {
		if (pollRepository.existsById(id)) {
			pollRepository.deleteById(id);
		}
	}

	@Transactional(readOnly = true)
	public List<Poll> findAll() {
		return pollRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Poll> findAll(SurveyUserDetails user) {
		return pollRepository.findByUser_Id(user.getId());
	}

	@Transactional(readOnly = true)
	public Optional<Poll> findById(Long id) {
		return pollRepository.findById(id);
	}
}
