package com.survey.surveyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.survey.surveyapi.dto.PollDTO;
import com.survey.surveyapi.exception.NotFoundException;
import com.survey.surveyapi.model.Poll;
import com.survey.surveyapi.security.SurveyUserDetails;
import com.survey.surveyapi.service.PollsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polls")
public class PollsResource {
	@Autowired
	private PollsService pollsService;

	public PollsResource(PollsService pollsService) {
		this.pollsService = pollsService;
	}

	@PostMapping
	@ResponseBody
	@Transactional
	public Poll create(HttpServletRequest request, HttpServletResponse response, @RequestBody PollDTO poll) throws Exception {
		if (request.authenticate(response)) {
			return pollsService.create(poll, (SurveyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		}
		return pollsService.create(poll);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Transactional
	public Poll update(@PathVariable(value = "id") Long id, @RequestBody @Valid PollDTO poll) {
		pollsService.findById(id).orElseThrow(() -> new NotFoundException("Poll", "id", id));
		return pollsService.update(poll);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public Poll remove(@PathVariable(value = "id") Long id) {
		Optional<Poll> pollRemoved = pollsService.findById(id);
		pollRemoved.orElseThrow(() -> new NotFoundException("Poll", "id", id));
		pollsService.remove(id);
		return pollRemoved.get();
	}

	@GetMapping
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Poll> findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.authenticate(response)) {
			return pollsService.findAll((SurveyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		}
		return pollsService.findAll();
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public Poll findById(@PathVariable(value = "id") Long id) {
		return pollsService.findById(id).orElseThrow(() -> new NotFoundException("Poll", "id", id));
	}
}
