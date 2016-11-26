package edu.utdallas.wpl.cookies.spring.biz.manager;

import java.util.List;

import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;

public interface TransactionServiceManager {

	public List<TransactionInfo> addTransaction(TransactionInfo transactionInfo);

	public List<TransactionInfo> getTranscation();

}
