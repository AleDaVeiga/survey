package com.survey.surveyapi.mail;

import java.util.Map;

import org.springframework.core.io.InputStreamSource;

public class EmailDTO {
	private String from;
	private String[] to;
	private String subject;
	private Map<String, Object> content;
	private Map<String, InputStreamSource> attachments;
	private boolean isHtml;

	public EmailDTO(String from, String[] to, String subject, Map<String, Object> content,
			Map<String, InputStreamSource> attachments, boolean isHtml) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.attachments = attachments;
		this.isHtml = isHtml;
	}

	public String getFrom() {
		return from;
	}

	public String[] getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public Map<String, InputStreamSource> getAttachments() {
		return attachments;
	}

	public boolean isHtml() {
		return isHtml;
	}
}
