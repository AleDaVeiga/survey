package com.survey.surveyapi.service;

import com.survey.surveyapi.dto.ChangePasswordDTO;
import com.survey.surveyapi.dto.UserDTO;
import com.survey.surveyapi.dto.UserValidateDTO;
import com.survey.surveyapi.model.User;

public interface UsersService {
	User create(UserDTO user);
	
	User update(UserDTO user);
	
	User updatePassword(ChangePasswordDTO user);
	
	User validate(UserValidateDTO user);
}
