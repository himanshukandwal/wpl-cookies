package edu.utdallas.wpl.cookies.front.end.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import edu.utdallas.wpl.cookies.front.end.config.CustomRestController;
import edu.utdallas.wpl.cookies.spring.common.dto.Address;

@CustomRestController
public class AddressController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/address", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createAddress(@RequestBody Map<String, Object> addressMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<Address> responseEntity = restTemplate.postForEntity(webserviceUrl + "/address", addressMap, Address.class);
				
		model.put("address", responseEntity.getBody());
		return model;
	}
	
	@RequestMapping("/addresses")
	public @ResponseBody Map<String, Object> getAddresses() {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<Address[]> responseEntity = restTemplate.getForEntity(webserviceUrl + "/addresses", Address[].class);
				
		model.put("addresses", responseEntity.getBody());
		return model;
	}

}