package com.survey.surveyapi.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = BAD_REQUEST)
public class NonUniqueException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String field;
	private Object value;

	public NonUniqueException(String name, String field, Object value) {
		super(String.format("Multiple %s are not allowed with %s: %s", name, field, value));

		this.setField(field);
		this.setName(name);
		this.setValue(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
