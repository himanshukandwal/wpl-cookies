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
import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;

@CustomRestController
public class TransactionController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${services.url}")
	private String webserviceUrl;
	
	@RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createTransaction(@RequestBody Map<String, Object> transactionMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<TransactionInfo> responseEntity = restTemplate.postForEntity(webserviceUrl + "/saveInterestedBid", transactionMap, TransactionInfo.class);
				
		model.put("transaction", responseEntity.getBody());
		return model;
	}
	
	@RequestMapping(value = "/updateTransaction/{bidStatus}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> updateTransaction(@PathVariable String bidStatus, @RequestBody Map<String, Object> transactionMap) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		restTemplate.put(webserviceUrl + "/updateBidStatus/" + bidStatus, transactionMap);
				
		TransactionInfo result = restTemplate.getForObject(webserviceUrl + "/getTransactionById/" + transactionMap.get("id"), TransactionInfo.class);

		model.put("transaction", result);
		return model;		
	}
	
	@RequestMapping(value = "/getTransactions/{bidId}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTransactionsByBId(@PathVariable Integer bidId) {
		Map<String,Object> model = new HashMap<String,Object>();
		
		// web service invocation.
		ResponseEntity<TransactionInfo[]> responseEntity = restTemplate.getForEntity(webserviceUrl + "/getTransactionByBid/" + bidId, TransactionInfo[].class);
				
		model.put("transaction", responseEntity.getBody());
		return model;
	}
	
}