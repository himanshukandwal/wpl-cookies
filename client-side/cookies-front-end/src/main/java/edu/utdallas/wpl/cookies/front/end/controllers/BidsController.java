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
import edu.utdallas.wpl.cookies.spring.common.dto.PublishedBids;

@CustomRestController
public class BidsController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/postBid/{token}", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> postBid(@PathVariable String token, @RequestBody Map<String, Object> bidMap) {
		Map<String,Object> model = new HashMap<String,Object>();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>> httpRequestEntity = new HttpEntity<Map<String, Object>>(bidMap, requestHeaders);

		// web service invocation.
		ResponseEntity<PublishedBids> responseEntity = restTemplate.exchange(webserviceUrl + "/addBidRequest", HttpMethod.POST, httpRequestEntity, PublishedBids.class);
				
		model.put("bid", responseEntity.getBody());
		return model;
	}
	
	@RequestMapping(value = "/getBids/{token}")
	public @ResponseBody Map<String, Object> getAllBids(@PathVariable String token) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>> httpRequestEntity = new HttpEntity<Map<String, Object>>(requestHeaders);

		// web service invocation.
		ResponseEntity<PublishedBids[]> responseEntity = restTemplate.exchange(webserviceUrl + "/getBids", HttpMethod.GET, httpRequestEntity, PublishedBids[].class);
				
		model.put("bid", responseEntity.getBody());
		return model;
	}
	
	@RequestMapping(value = "/getBids/{timestamp}/{token}")
	public @ResponseBody Map<String, Object> getAllBids(@PathVariable Long timestamp, @PathVariable String token) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>> httpRequestEntity = new HttpEntity<Map<String, Object>>(requestHeaders);

		// web service invocation.
		ResponseEntity<PublishedBids[]> responseEntity = restTemplate.exchange(webserviceUrl + "/getActiveBids/" + timestamp, HttpMethod.GET, httpRequestEntity, PublishedBids[].class);
				
		model.put("bid", responseEntity.getBody());
		return model;
	}
	
}