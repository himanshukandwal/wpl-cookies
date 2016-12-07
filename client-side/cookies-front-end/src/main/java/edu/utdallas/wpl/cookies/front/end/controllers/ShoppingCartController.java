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
import edu.utdallas.wpl.cookies.spring.common.dto.ShoppingInfo;

@CustomRestController
public class ShoppingCartController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/checkoutCart/{token}", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkoutCart(@PathVariable String token, @RequestBody Map<String, Object>[] transactionMaps) {
		Map<String,Object> model = new HashMap<String,Object>();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>[]> httpRequestEntity = new HttpEntity<Map<String, Object>[]>(transactionMaps, requestHeaders);

		// web service invocation.
		ResponseEntity<ShoppingInfo[]> responseEntity = restTemplate.exchange(webserviceUrl + "/checkout", HttpMethod.POST, httpRequestEntity, ShoppingInfo[].class);
		
		model.put("transactions", responseEntity.getBody());
		return model;
	}
	
}