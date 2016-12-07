package edu.utdallas.wpl.cookies.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;

public interface TransactionInfoRestService {

	ResponseEntity<List<TransactionInfo>> getTransaction();
	
	ResponseEntity<TransactionInfo> saveBidInterested(TransactionInfo transactionInfo);
	
	ResponseEntity<TransactionInfo> updateBidStatus(TransactionInfo transactionInfo,String bidStatusCode);
	
    ResponseEntity<String> deleteTransaction(Integer transId);
    
    ResponseEntity<List<TransactionInfo>>  getTransactionByBid(Integer bidId);
    
    ResponseEntity<TransactionInfo>  getTransactionById(Integer transId);
	
}
