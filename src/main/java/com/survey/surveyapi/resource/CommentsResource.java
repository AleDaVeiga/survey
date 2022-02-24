package com.survey.surveyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.survey.surveyapi.exception.NotFoundException;
import com.survey.surveyapi.model.Comment;
import com.survey.surveyapi.security.SurveyUserDetails;
import com.survey.surveyapi.service.CommentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polls")
public class CommentsResource {
	@Autowired
	private CommentsService commentsService;

	@PostMapping("/{pollid}/comments")
	@Transactional
	public Comment create(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "pollid") Long pollId, @RequestParam("description") String description) throws Exception {
		if (request.authenticate(response)) {
			return commentsService.create(pollId, description, (SurveyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		}
		return commentsService.create(pollId, description);
	}

	@PutMapping("/{pollid}/comments/{id}")
	@Transactional
	@PreAuthorize("isAuthenticated()")
	public Comment update(@PathVariable(value = "pollid") Long pollId, @PathVariable(value = "id") Long id, @RequestParam("description") String description) throws Exception {
		return commentsService.update(id, description);
	}

	@DeleteMapping("/{pollid}/comments/{id}")
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Comment remove(@PathVariable(value = "pollid") Long pollId, @PathVariable(value = "id") Long id) {
		Optional<Comment> comment = commentsService.findById(id);
		comment.orElseThrow(() -> new NotFoundException("Comment", "id", id));
		commentsService.remove(id);
		return comment.get();
	}

	@GetMapping("/{pollid}/comments")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Comment> findAll(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "pollid") Long pollId) throws Exception {
		if (request.authenticate(response)) {
			return commentsService.findAll(pollId, (SurveyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		}
		return commentsService.findAll(pollId);
	}

	@GetMapping("/{pollid}/comments/{id}")
	@Transactional(readOnly = true)
	public Comment findById(HttpServletRequest request, @PathVariable(value = "pollid") Long pollId, @PathVariable(value = "id") Long id) {
		return commentsService.findById(id).orElseThrow(() -> new NotFoundException("Comment", "id", id));
	}
}
