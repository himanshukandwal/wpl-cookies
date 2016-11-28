package edu.utdallas.wpl.cookies.spring.biz.manager;

import java.util.List;

import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;

public interface TransactionServiceManager {

	public TransactionInfo addTransaction(TransactionInfo transactionInfo);

	public List<TransactionInfo> getTranscation();
	
	public TransactionInfo updateTransaction(TransactionInfo transactionInfo);
	
	public Integer deleteTransaction(Integer transId);
	
	public List<TransactionInfo> getTransactionsByBidId(Integer bidId);

}
