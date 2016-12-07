package edu.utdallas.wpl.cookies.spring.biz.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utdallas.wpl.cookies.spring.biz.manager.TransactionServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;
import edu.utdallas.wpl.cookies.spring.dao.orm.TransactionInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.TransactionInfoRepository;

@Service
public class TransactionServiceManagerImpl  implements TransactionServiceManager{

	@Autowired
	private TransactionInfoRepository transactionInfoRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public TransactionInfo addTransaction(TransactionInfo transactionInfo) {
		return mapper.map(transactionInfoRepository.save(mapper.map(transactionInfo, TransactionInfoEntity.class)), TransactionInfo.class);
	}

	@Override
	public List<TransactionInfo> getTranscation() {
		List<TransactionInfoEntity> transactionInfoEntityList = transactionInfoRepository.getAll();
	
		List<TransactionInfo> transactionInfoList = null;
		
		if (transactionInfoEntityList != null) {
			transactionInfoList = new ArrayList<>();
			for (TransactionInfoEntity transactionInfoEntity : transactionInfoEntityList) {
				TransactionInfo transactionInfo = mapper.map(transactionInfoEntity, TransactionInfo.class);
				transactionInfoList.add(transactionInfo);
			}
		}

		return transactionInfoList;
	}

	@Override
	public TransactionInfo updateTransaction(TransactionInfo transactionInfo) {
		transactionInfoRepository.update(mapper.map(transactionInfo, TransactionInfoEntity.class));
		return transactionInfo;
	}

	@Override
	public Integer deleteTransaction(Integer transId) {
		TransactionInfoEntity transactionInfoEntity = transactionInfoRepository.get(transId);
		if (transactionInfoEntity != null) {
			transactionInfoRepository.delete(transactionInfoEntity);
			return 0;
		}
		return -1;
	}

	@Override
	public List<TransactionInfo> getTransactionsByBidId(Integer bidId) {

		List<TransactionInfoEntity> transactionInfoEntityList = transactionInfoRepository
				.getAllTransactionsByBidId(bidId);
		List<TransactionInfo> transactionInfoList = null;
		if (transactionInfoEntityList != null) {
			transactionInfoList = new ArrayList<>();
			for (TransactionInfoEntity transactionInfoEntity : transactionInfoEntityList) {
				TransactionInfo transactionInfo = mapper.map(transactionInfoEntity, TransactionInfo.class);
				transactionInfoList.add(transactionInfo);
			}
		}

		return transactionInfoList;
	}

	@Override
	public TransactionInfo getTransactionsById(Integer transId) {
		 TransactionInfoEntity transactionInfoEntity = transactionInfoRepository.get(transId);
		 TransactionInfo transactionInfo = mapper.map(transactionInfoEntity, TransactionInfo.class);
		 return transactionInfo;
	}
	
}
