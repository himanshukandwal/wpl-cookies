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
import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;

@CustomRestController
public class TransactionController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/createTransaction/{token}", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createTransaction(@PathVariable String token, @RequestBody Map<String, Object> transactionMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>> httpRequestEntity = new HttpEntity<Map<String, Object>>(transactionMap, requestHeaders);

		// web service invocation.
		ResponseEntity<TransactionInfo> responseEntity = restTemplate.exchange(webserviceUrl + "/saveInterestedBid", HttpMethod.POST, httpRequestEntity, TransactionInfo.class);
						
		model.put("transaction", responseEntity.getBody());
		return model;
	}
	
	@RequestMapping(value = "/updateTransaction/{bidStatus}/{token}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> updateTransaction(@PathVariable String bidStatus, @PathVariable String token, @RequestBody Map<String, Object> transactionMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>> httpRequestEntity = new HttpEntity<Map<String, Object>>(transactionMap, requestHeaders);
		
		// web service invocation.
		ResponseEntity<TransactionInfo> responseEntity = restTemplate.exchange(webserviceUrl + "/updateBidStatus/" + bidStatus, HttpMethod.PUT, httpRequestEntity, TransactionInfo.class);
				
		model.put("transaction", responseEntity.getBody());
		return model;		
	}
	
	@RequestMapping(value = "/getTransactions/{bidId}/{token}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTransactionsByBId(@PathVariable Integer bidId, @PathVariable String token) {
		Map<String,Object> model = new HashMap<String,Object>();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		
		HttpEntity<Map<String, Object>> httpRequestEntity = new HttpEntity<Map<String, Object>>(requestHeaders);
		
		// web service invocation.
		ResponseEntity<TransactionInfo[]> responseEntity = restTemplate.exchange(webserviceUrl + "/getTransactionByBid/" + bidId, HttpMethod.GET, httpRequestEntity, TransactionInfo[].class);
				
		model.put("transaction", responseEntity.getBody());
		return model;
	}
	
}