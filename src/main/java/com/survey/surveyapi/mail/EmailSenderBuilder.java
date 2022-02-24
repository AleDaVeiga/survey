package com.survey.surveyapi.mail;

import java.util.Properties;

import com.survey.surveyapi.model.EmailConfig;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderBuilder {
	public JavaMailSenderImpl build(EmailConfig configuracao) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(configuracao.getHost());
		mailSender.setPort(configuracao.getPort());
		mailSender.setUsername(configuracao.getUsername());
		mailSender.setPassword(configuracao.getPassword());

		Properties properties = mailSender.getJavaMailProperties();
		// properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", configuracao.getSmtpAuth());
		properties.put("mail.smtp.starttls.enable", configuracao.getSmtpStarttlsEnable());
		// properties.put("mail.debug", "true");
		return mailSender;
	}
}
