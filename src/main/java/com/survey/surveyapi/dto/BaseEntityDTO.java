package com.survey.surveyapi.dto;

import static java.lang.Long.valueOf;
import static org.springframework.util.StringUtils.isEmpty;

public class BaseEntityDTO {
	protected Long id;
	
	public BaseEntityDTO() {
		super();
	}
	
	public BaseEntityDTO(String id) {
		super();
		if (!isEmpty(id)) {
			setId(valueOf(id));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
