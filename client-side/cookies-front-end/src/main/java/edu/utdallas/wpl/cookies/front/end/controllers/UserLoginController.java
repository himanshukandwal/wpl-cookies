package edu.utdallas.wpl.cookies.front.end.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		UserInformation result = restTemplate.postForObject(webserviceUrl + "/userlogin", userInformationMap, UserInformation.class);
		
		model.put("status", (result != null) ? "success" : "failure");
		model.put("userInfo", result);
		return model;
	}
	
	@RequestMapping(value = "/checkLogin/{email}/{password}")
	public Map<String,Object> getUserInfo(@PathVariable String email, @PathVariable String password){
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		UserInformation result = restTemplate.getForObject(webserviceUrl + "/userlogin/"+email+"/"+password, UserInformation.class);
	    
		model.put("status", "success");
		model.put("userInfo", result);
		return model;
	}
	
	@RequestMapping(value = "/contactus" , method = RequestMethod.POST)
	public Map<String,Object> saveContactUs(@RequestBody Map<String, Object> userInformationMap){
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		//UserInformation result = restTemplate.getForObject(webserviceUrl + "/userlogin/"+email+"/"+password, UserInformation.class);
	    System.out.println("FirstName>>>>>>>>>>> "+userInformationMap.get("firstName"));
		model.put("status", "success");
		//model.put("userInfo", new Object());
		return model;
	}

}