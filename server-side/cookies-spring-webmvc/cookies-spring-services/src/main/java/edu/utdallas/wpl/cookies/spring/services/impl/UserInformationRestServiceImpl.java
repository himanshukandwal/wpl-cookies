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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.utdallas.wpl.cookies.spring.biz.manager.UserInformationServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;
import edu.utdallas.wpl.cookies.spring.services.UserInformationRestService;

@Controller
@RequestMapping("/api")
public  class UserInformationRestServiceImpl implements UserInformationRestService {

    private static final Logger LOG = LoggerFactory.getLogger(UserInformationRestService.class);

	@Autowired
	private UserInformationServiceManager userInformationServiceManager;

	@Override
	@RequestMapping(value = "/userinfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserInformation> createLogin(@RequestBody UserInformation userInformation, HttpServletRequest request) {
		UserInformation persistedUser = userInformationServiceManager.createUserInformation(userInformation);
		
		LOG.info(" created user with id :" + persistedUser.getId());
		return ResponseEntity.ok(persistedUser);
	}
	
	@Override
	@RequestMapping(value = "/userinfo/{email}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserInformation> authenticateUser(@PathVariable String email, @PathVariable String password) {
		UserInformation userInformation = userInformationServiceManager.getUserInformationByEmail(email);
		if (userInformation == null || !userInformation.getPassword().equals(password)) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		return ResponseEntity.ok(userInformation);	
	}
	
	@Override
	@RequestMapping(value = "/userinfo/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserInformation> getUserInformation(@PathVariable Integer id) {
		return ResponseEntity.ok(userInformationServiceManager.getUserInformation(id));
	}

	@SuppressWarnings("rawtypes")
	@Override
	@RequestMapping(value = "/userinfo", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity updateUserInformation(@RequestBody UserInformation userInformation) {
		if (userInformation.getId() == null || userInformationServiceManager.getUserInformation(userInformation.getId()) == null)
			return ResponseEntity.badRequest().body("user not found");
		UserInformation updatedUserInformation = userInformationServiceManager.updateUserInformation(userInformation);
		
		LOG.info(" updated user with id :" + updatedUserInformation.getId());
		
		return ResponseEntity.ok(updatedUserInformation);
	}

	@Override
	@RequestMapping(value = "/userinfo", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUserInformation(@RequestBody UserInformation userInformation) {
		if (userInformation.getEmail() == null || userInformationServiceManager.getUserInformation(userInformation.getId()) == null)
			return ResponseEntity.badRequest().body("user not found");
		else 
			userInformationServiceManager.deleteUserInformation(userInformation);
		
		LOG.info("deleted address with email : " + userInformation.getEmail());
		
		return ResponseEntity.ok("success");
	}
    
}