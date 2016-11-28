package edu.utdallas.wpl.cookies.spring.services.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.utdallas.wpl.cookies.spring.biz.manager.TransactionServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;
import edu.utdallas.wpl.cookies.spring.common.enums.BidStatus;
import edu.utdallas.wpl.cookies.spring.services.TransactionInfoRestService;

@Controller
@RequestMapping("/api")
public class TransactionInfoRestServiceImpl implements TransactionInfoRestService {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionInfoRestService.class);
	@Autowired
	private TransactionServiceManager transactionManager;
	@Override
	@RequestMapping(value = "/getTransactions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TransactionInfo>> getTransaction() {

		List<TransactionInfo> transactionInfoList = transactionManager.getTranscation();
		if (transactionInfoList != null) {
			LOG.info(" The number of bid requests for user  :" + transactionInfoList.size());
			return ResponseEntity.ok(transactionInfoList);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@Override
	@RequestMapping(value = "/saveInterestedBid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TransactionInfo> saveBidInterested(@RequestBody  TransactionInfo interstedBidRequest,HttpServletRequest request) {
  
		
		System.out.println("inside transaction>>>>");
		interstedBidRequest.setBidStatus(BidStatus.INTERESTED.toString());

		TransactionInfo transactionInfo = transactionManager.addTransaction(interstedBidRequest);

		LOG.info(" created user with id :" + transactionInfo.getTranId());

		return ResponseEntity.ok(transactionInfo);

	}
	
	
	@Override
	@RequestMapping(value = "/updateInterestedBid", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TransactionInfo> updateBidInterested(@RequestBody  TransactionInfo interstedBidRequest,HttpServletRequest request) {
  
		interstedBidRequest.setBidStatus(BidStatus.INTERESTED.toString());

		TransactionInfo transactionInfo = transactionManager.updateTransaction(interstedBidRequest);

		LOG.info(" created user updated   :" + transactionInfo.getTranId());

		return ResponseEntity.ok(transactionInfo);

	}
	
	@Override
	@RequestMapping(value = "/updateBidFinalized", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TransactionInfo> updateBidFinalized(@RequestBody  TransactionInfo finalizedBidRequest, String bidStatusCode,HttpServletRequest request) {
  
		finalizedBidRequest.setBidStatus(BidStatus.valueOf(bidStatusCode).toString());

		TransactionInfo transactionInfo = transactionManager.updateTransaction(finalizedBidRequest);

		LOG.info(" created user updated   :" + transactionInfo.getTranId());

		return ResponseEntity.ok(transactionInfo);

	}

	@Override
	@RequestMapping(value = "/deleteTransaction/{transId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteTransaction(@PathVariable Integer transId) {
		Integer returnCode =transactionManager.deleteTransaction(transId);
		if(returnCode==-1){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok("success");
	}
	
	@Override
	@RequestMapping(value = "/getTransactionByBid/{bidId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionInfo>> getTransactionByBid(@PathVariable Integer bidId) {
		List<TransactionInfo> transactionInfoList	=transactionManager.getTransactionsByBidId(bidId);
		if(transactionInfoList==null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(transactionInfoList);
	}
	
	
	
	
}
