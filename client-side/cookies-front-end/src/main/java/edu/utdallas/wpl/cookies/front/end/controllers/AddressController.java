package edu.utdallas.wpl.cookies.front.end.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.utdallas.wpl.cookies.spring.common.dto.Address;

@RestController
public class AddressController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/address", method = RequestMethod.POST)
	public Map<String, Object> createAddress(@RequestBody Map<String, Object> addressMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<Address> responseEntity = restTemplate.postForEntity(webserviceUrl + "/addresses", addressMap, Address.class);
				
		model.put("address", responseEntity.getBody());
		return model;
	}
	
	@RequestMapping("/addresses")
	public Map<String, Object> getAddresses() {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<Address[]> responseEntity = restTemplate.getForEntity(webserviceUrl + "/addresses", Address[].class);
				
		model.put("addresses", responseEntity.getBody());
		return model;
	}

}