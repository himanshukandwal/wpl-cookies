package edu.utdallas.wpl.cookies.front.end.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;

@RestController
public class UserLoginController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public Map<String, Object> ping() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("time", new Date());
		return model;
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public Map<String, Object> createUser(@RequestBody Map<String, Object> userInformationMap) {
		System.out.println("inside post method");
		Map<String,Object> model = new HashMap<String,Object>();
		UserInformation result = null;
		try {
			UserInformation userInformation = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(userInformationMap), UserInformation.class);
			result = restTemplate.postForObject(webserviceUrl + "/userlogin", userInformation, UserInformation.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.put("status", (result != null) ? "success" : "failure");
		return model;
	}

}