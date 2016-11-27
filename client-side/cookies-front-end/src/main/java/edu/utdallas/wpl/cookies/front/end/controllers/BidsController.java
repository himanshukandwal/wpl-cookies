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
import org.springframework.web.client.RestTemplate;

import edu.utdallas.wpl.cookies.front.end.config.CustomRestController;
import edu.utdallas.wpl.cookies.spring.common.dto.PublishedBids;

@CustomRestController
public class BidsController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/postBid", method = RequestMethod.POST)
	public Map<String, Object> postBid(@RequestBody Map<String, Object> bidMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<PublishedBids> responseEntity = restTemplate.postForEntity(webserviceUrl + "/addBidRequest", bidMap, PublishedBids.class);
				
		model.put("bid", responseEntity.getBody());
		return model;
	}
	
	@RequestMapping(value = "/myBids/{userId}", method = RequestMethod.POST)
	public Map<String, Object> getMyBids(@PathVariable String userId, @RequestBody Map<String, Object> addressMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<PublishedBids> responseEntity = restTemplate.postForEntity(webserviceUrl + "/getBids/" + userId, addressMap, PublishedBids.class);
				
		model.put("bid", responseEntity.getBody());
		return model;
	}
	
}