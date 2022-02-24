package com.survey.surveyapi.mail;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.InputStreamSource;

public class EmailBuilder {
	private String source;
	private Optional<String> from;
	private List<String> to;
	private Optional<String> subject;
	private Map<String, Object> content;
	private Map<String, InputStreamSource> attachments;
	private boolean isHtml;

	public EmailBuilder() {
		super();
		this.source = "no-reply@survey.com";
		this.from = of(source);
		this.to = new ArrayList<>();
		this.content = new HashMap<>();
		this.attachments = new HashMap<>();
		this.isHtml = false;
	}

	public EmailDTO build() {
		return new EmailDTO(from.orElse(source), to.stream().toArray(String[]::new), subject.orElse("No subject"), content, attachments, isHtml);
	}

	public EmailBuilder from(String from) {
		this.from = ofNullable(from);
		return this;
	}

	public EmailBuilder to(List<String> to) {
		this.to.addAll(to);
		return this;
	}

	public EmailBuilder subject(String subject) {
		this.subject = ofNullable(subject);
		return this;
	}

	public EmailBuilder content(String model, Object content) {
		this.content.put(model, content);
		return this;
	}

	public EmailBuilder attachment(String name, InputStreamSource file) {
		this.attachments.put(name, file);
		return this;
	}

	public EmailBuilder html() {
		this.isHtml = true;
		return this;
	}
}
