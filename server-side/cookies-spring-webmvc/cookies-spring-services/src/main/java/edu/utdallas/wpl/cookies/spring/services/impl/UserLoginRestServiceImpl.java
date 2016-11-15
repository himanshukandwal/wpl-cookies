package edu.utdallas.wpl.cookies.spring.services.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		UserInformation persistedUser = userInformationServiceManager.createUserInformation(userInformation);
		
		LOG.info(" created user with id :" + persistedUser.getId());
		
		return ResponseEntity.ok(persistedUser );
	}
	
	@Override
	@RequestMapping(value = "/userlogin/{email}/{password}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticateUser(@PathVariable String email, @PathVariable String password) {
		UserInformation userInformation = userInformationServiceManager.getUserInformationByEmail(email);
		
		if (userInformation == null || !userInformation.getPassword().equals(password)) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("either username is invalid or password is incorrect !");
		return ResponseEntity.ok("success !");	
	}
	
	@Override
	@RequestMapping(value = "/userlogin/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInformation> getUserInformation(@PathVariable Integer id) {
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