package edu.utdallas.wpl.cookies.spring.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;

public interface TransactionInfoRestService {

	
	ResponseEntity<List<TransactionInfo>> getTransaction();
	ResponseEntity<TransactionInfo> saveBidInterested(TransactionInfo transactionInfo,HttpServletRequest request);
	ResponseEntity<TransactionInfo> updateBidStatus(TransactionInfo transactionInfo,String bidStatusCode,HttpServletRequest request);
    ResponseEntity<String> deleteTransaction(Integer transId);
    ResponseEntity<List<TransactionInfo>>  getTransactionByBid(Integer bidId);
	
}
