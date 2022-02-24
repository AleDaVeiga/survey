package com.survey.surveyapi.service;

import com.survey.surveyapi.dto.SendEmailStatusDTO;
import com.survey.surveyapi.mail.EmailDTO;

public interface EmailService {
	SendEmailStatusDTO sendHtml(String template, EmailDTO email);
}
