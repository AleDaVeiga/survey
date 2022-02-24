package com.survey.surveyapi.mail;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailContentBuilder {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailContentBuilder.class);
	
	@Autowired
	private TemplateEngine templateEngine;

	public String build(String template, Map<String, Object> conteudo) {
		Context context = new Context();
		if (conteudo != null) {
			conteudo.forEach((key,value) -> context.setVariable(key, value));
			return templateEngine.process(template, context);
		}
		LOGGER.error(String.format("Problem with the template {} for the body of email", template));
		return "";
	}
}
