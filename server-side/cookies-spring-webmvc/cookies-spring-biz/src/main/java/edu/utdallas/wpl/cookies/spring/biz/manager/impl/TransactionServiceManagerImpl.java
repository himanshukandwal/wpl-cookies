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
	TransactionInfoRepository transactionInfoRepository;
	@Autowired
	private Mapper mapper;
	@Override
	public List<TransactionInfo> addTransaction(TransactionInfo transactionInfo) {
		
		return null;
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
	
	

}
