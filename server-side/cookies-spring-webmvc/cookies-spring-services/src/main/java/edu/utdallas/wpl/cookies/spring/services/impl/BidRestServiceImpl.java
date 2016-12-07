package edu.utdallas.wpl.cookies.spring.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.utdallas.wpl.cookies.spring.biz.manager.BidServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.PublishedBids;
import edu.utdallas.wpl.cookies.spring.services.BidRestService;

@Controller
@RequestMapping("/api")
public class BidRestServiceImpl implements BidRestService {
	
	@Autowired
	private BidServiceManager bidServiceManager;
	
    private static final Logger LOG = LoggerFactory.getLogger(BidRestService.class);
    
	@Override
	@RequestMapping(value = "/addBidRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PublishedBids> createBidRequest(@RequestBody PublishedBids publishedBids, HttpServletRequest request) {
		PublishedBids persistedBids = bidServiceManager.addBidRequest(publishedBids);
		
		LOG.info(" created bid with id :" + persistedBids.getBidId());
		
		return ResponseEntity.ok(persistedBids);
	}
	
	@Override
	@RequestMapping(value ="/getBids/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PublishedBids>> viewMyBidRequests(@PathVariable Integer userId) {
		List<PublishedBids> publishedBids = bidServiceManager.getBidRequests(userId);
		
		if (publishedBids == null) 
			publishedBids = new ArrayList<>();
		
		LOG.info(" The number of bid requests for user  :" + publishedBids.size());
		return ResponseEntity.ok(publishedBids);
	}
	
	@Override
	@RequestMapping(value = "/deleteBids", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBidRequest(Integer bidId) {
		bidServiceManager.deleteBidRequest(bidId);
	}
	
	@Override
	@RequestMapping(value ="/getBids", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PublishedBids>> viewAllBidRequests() {
		List<PublishedBids> publishedBids = bidServiceManager.getBidRequests();
		
		if (publishedBids == null) 
			publishedBids = new ArrayList<>();
		
		LOG.info(" The number of bid requests for user  :" + publishedBids.size());
		return ResponseEntity.ok(publishedBids);
	}
	
	@Override
	@RequestMapping(value ="/getActiveBids/{timestamp}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PublishedBids>> viewAllActiveBids(@PathVariable Long timestamp) {
		List<PublishedBids> publishedBids = bidServiceManager.getlatestBids(timestamp);
		
		if (publishedBids == null) 
			publishedBids = new ArrayList<>();
		
		LOG.info(" The number of bid requests for user  :" + publishedBids.size());
		return ResponseEntity.ok(publishedBids);
	}
	
}