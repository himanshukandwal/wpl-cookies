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
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody ResponseEntity<UserInformation> createLogin(UserInformation userInformation, HttpServletRequest request) {
		UserInformation persistedUser = userInformationServiceManager.createUserInformation(userInformation);
		
		LOG.info(" created user with id :" + persistedUser.getId());
		
		return ResponseEntity.ok(persistedUser );
	}
	
	@Override
	@RequestMapping(value = "/userlogin/{email}/{password}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> authenticateUser(@PathVariable String email, @PathVariable String password) {
		UserInformation userInformation = userInformationServiceManager.getUserInformationByEmail(email);
		
		if (userInformation == null || !userInformation.getPassword().equals(password)) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("either username is invalid or password is incorrect !");
		return ResponseEntity.ok("success !");	
	}
	
	@Override
	@RequestMapping(value = "/userlogin/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserInformation> getUserInformation(@PathVariable Integer id) {
		return ResponseEntity.ok(userInformationServiceManager.getUserInformation(id));
	}

	@Override
	public @ResponseBody ResponseEntity<UserInformation> updateUserInformation(UserInformation userInformation) {
		UserInformation updatedUserInformation = userInformationServiceManager.updateUserInformation(userInformation);
		
		LOG.info(" updated user with id :" + updatedUserInformation.getId());
		
		return ResponseEntity.ok(updatedUserInformation);
	}

	@Override
	@RequestMapping(value = "/userlogin/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUserInformation(@PathVariable Integer id) {
		if (null == id)
			return ResponseEntity.badRequest().body("address id is empty");
		
		else if (userInformationServiceManager.getUserInformation(id) == null)
			return ResponseEntity.badRequest().body("address not found");
		else 
			userInformationServiceManager.deleteUserInformation(id);
		
		LOG.info("deleted address with id : " + id);
		
		return ResponseEntity.ok("success");
	}
    
}