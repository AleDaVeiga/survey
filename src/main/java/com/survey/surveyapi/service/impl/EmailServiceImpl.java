package com.survey.surveyapi.service.impl;

import java.util.Map.Entry;

import com.survey.surveyapi.dto.SendEmailStatusDTO;
import com.survey.surveyapi.mail.EmailContentBuilder;
import com.survey.surveyapi.mail.EmailDTO;
import com.survey.surveyapi.mail.EmailSenderBuilder;
import com.survey.surveyapi.repository.EmailConfigRepository;
import com.survey.surveyapi.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private EmailConfigRepository emailConfigRepository;

	@Autowired
	private EmailSenderBuilder senderBuilder;

	@Autowired
	private EmailContentBuilder contentBuilder;

	public SendEmailStatusDTO sendHtml(String template, EmailDTO email) {
		return sendMessage(template, email);
	}

	private SendEmailStatusDTO sendMessage(String template, EmailDTO email) {
		String body = contentBuilder.build(template, email.getContent());

		MimeMessagePreparator messagePreparator = createMessage(email, body);
		try {
			JavaMailSenderImpl emailSender = senderBuilder.build(emailConfigRepository.findFirstByOrderByIdDesc());
			emailSender.send(messagePreparator);
			LOGGER.info("Sending email '{}' to: {}", email.getSubject(), email.getTo());
			return new SendEmailStatusDTO(email, body).success();
		} catch (Exception e) {
			LOGGER.error("Problem to send the email {} to: {}, error message: {}", email.getTo(), e.getMessage());
			return new SendEmailStatusDTO(email, body).error(e.getMessage());
		}
	}

	private MimeMessagePreparator createMessage(EmailDTO email, String body) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("no-replay@survey.com");
			helper.setTo(email.getTo());
			helper.setSubject(email.getSubject());
			helper.setText(body, email.isHtml());
			for (Entry<String, InputStreamSource> attachment : email.getAttachments().entrySet()) {
				helper.addAttachment(attachment.getKey(), attachment.getValue());
			}
		};
		return messagePreparator;
	}
}
