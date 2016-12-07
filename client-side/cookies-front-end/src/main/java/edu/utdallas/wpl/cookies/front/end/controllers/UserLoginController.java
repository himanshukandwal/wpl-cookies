package edu.utdallas.wpl.cookies.front.end.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import edu.utdallas.wpl.cookies.front.end.config.CustomRestController;
import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;

@CustomRestController
public class UserLoginController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createUser(@RequestBody Map<String, Object> userInformationMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<UserInformation> resultEntity = restTemplate.postForEntity(webserviceUrl + "/userinfo", userInformationMap, UserInformation.class);
		
		model.put("status", (resultEntity.getBody() != null) ? "success" : "failure");
		model.put("userInfo", resultEntity.getBody());
		model.put("userToken", resultEntity.getHeaders().get("X-Cookies-Token"));
		return model;
	}
	
	@RequestMapping(value = "/checkLogin/{email}/{password}")
	public @ResponseBody Map<String,Object> getUserInfo(@PathVariable String email, @PathVariable String password){
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<UserInformation> resultEntity = restTemplate.getForEntity(webserviceUrl + "/userinfo/" + email + "/" + password, UserInformation.class);
	    
		model.put("status", "success");
		model.put("userInfo", resultEntity.getBody());
		model.put("userToken", resultEntity.getHeaders().get("X-Cookies-Token"));
		return model;
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> updateUser(@RequestBody Map<String, Object> userInformationMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		restTemplate.put(webserviceUrl + "/userinfo", userInformationMap);
		
		UserInformation result = restTemplate.getForObject(webserviceUrl + "/userinfo/" 
									+ userInformationMap.get("email") + "/" + userInformationMap.get("password"), UserInformation.class);
		
		model.put("status", (result != null) ? "success" : "failure");
		model.put("userInfo", result);
		return model;
	}

}