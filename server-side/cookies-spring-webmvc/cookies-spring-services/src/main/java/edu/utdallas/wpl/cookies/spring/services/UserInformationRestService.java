package edu.utdallas.wpl.cookies.spring.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;

public interface UserInformationRestService {

	ResponseEntity<UserInformation> createLogin(UserInformation userInformation,  HttpServletRequest request);

	ResponseEntity<UserInformation> getUserInformation(Integer id);
	
	ResponseEntity<UserInformation> authenticateUser(String email, String password);
	
	ResponseEntity updateUserInformation(String email, UserInformation userInformation);
	
	ResponseEntity<String> deleteUserInformation(String email);
	
}
