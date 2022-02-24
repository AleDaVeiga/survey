package com.survey.surveyapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PollDTO extends BaseEntityDTO {
	@NotBlank
	@Size(max = 255)
	private String title;

	@NotBlank
	@Size(max = 255)
	private String description;

	@NotBlank
	@Size(max = 255)
	private String options;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
}
