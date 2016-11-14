package edu.utdallas.wpl.cookies.spring.services.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.utdallas.wpl.cookies.spring.biz.manager.UserInformationServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;
import edu.utdallas.wpl.cookies.spring.services.UserLoginRestService;

@Controller
@RequestMapping("/api")
public  class UserLoginRestServiceImpl implements UserLoginRestService {

    private static final Logger LOG = LoggerFactory.getLogger(UserLoginRestService.class);

	@Autowired
	private UserInformationServiceManager userInformationServiceManager;


	@Override
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInformation> createLogin(UserInformation userInformation, HttpServletRequest request) {
		UserInformation persisted = userInformationServiceManager.createUserInformation(userInformation);
		return ResponseEntity.ok(persisted);
	}
	
	@Override
	public ResponseEntity<UserInformation> getUserInformation(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserInformation> updateUserInformation(UserInformation userInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteUserInformation(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
    
}