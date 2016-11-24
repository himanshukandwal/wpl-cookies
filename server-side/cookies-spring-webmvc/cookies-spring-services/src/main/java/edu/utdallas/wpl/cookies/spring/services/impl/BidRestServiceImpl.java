package edu.utdallas.wpl.cookies.spring.services.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.utdallas.wpl.cookies.spring.common.dto.PublishedBids;
import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;
import edu.utdallas.wpl.cookies.spring.services.BidRestService;

@Controller
@RequestMapping("/api")
public class BidRestServiceImpl  implements BidRestService{
	
    private static final Logger LOG = LoggerFactory.getLogger(UserInformation.class);
	
    @Override
	@RequestMapping(value = "/addbidrequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PublishedBids> createBidRequest(@RequestBody PublishedBids publishedBids, HttpServletRequest request) {
		
		return ResponseEntity.ok(null);
	}
	
	@Override
	@RequestMapping(value = "/getbids", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PublishedBids>> viewBidResponse(Integer bidId) {
		return ResponseEntity.ok(null);
	}
	
}
