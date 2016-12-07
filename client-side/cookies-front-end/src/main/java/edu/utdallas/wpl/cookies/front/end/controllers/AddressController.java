package edu.utdallas.wpl.cookies.front.end.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import edu.utdallas.wpl.cookies.front.end.config.CustomRestController;
import edu.utdallas.wpl.cookies.spring.common.dto.Address;

@CustomRestController
public class AddressController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/address/{token}", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createAddress(@PathVariable String token, @RequestBody Map<String, Object> addressMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>> httpRequestEntity = new HttpEntity<Map<String, Object>>(addressMap, requestHeaders);

		// web service invocation.
		ResponseEntity<Address> responseEntity = restTemplate.exchange(webserviceUrl + "/address", HttpMethod.POST, httpRequestEntity, Address.class);
				
		model.put("address", responseEntity.getBody());
		return model;
	}
	
	@RequestMapping("/addresses/{token}")
	public @ResponseBody Map<String, Object> getAddresses(@PathVariable String token) {
		Map<String,Object> model = new HashMap<String,Object>();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>> httpRequestEntity = new HttpEntity<Map<String, Object>>(requestHeaders);

		// web service invocation.
		ResponseEntity<Address[]> responseEntity = restTemplate.exchange(webserviceUrl + "/addresses", HttpMethod.GET, httpRequestEntity, Address[].class);

		model.put("addresses", responseEntity.getBody());
		return model;
	}

}