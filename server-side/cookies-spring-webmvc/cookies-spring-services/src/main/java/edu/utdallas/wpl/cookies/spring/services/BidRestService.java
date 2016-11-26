package edu.utdallas.wpl.cookies.spring.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import edu.utdallas.wpl.cookies.spring.common.dto.PublishedBids;

public interface BidRestService {
	
	ResponseEntity<PublishedBids>  createBidRequest(PublishedBids publishedBids,HttpServletRequest request);
	
	//this method will return all the response for a particular bidId
	ResponseEntity<List<PublishedBids>> viewMyBidRequests(Integer bidId);
    
	void deleteBidRequest(Integer bidId);
	
	ResponseEntity<List<PublishedBids>> viewAllBidRequests();
}
