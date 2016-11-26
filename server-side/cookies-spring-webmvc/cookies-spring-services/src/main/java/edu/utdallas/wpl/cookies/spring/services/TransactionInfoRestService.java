package edu.utdallas.wpl.cookies.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;

public interface TransactionInfoRestService {

	
	ResponseEntity<List<TransactionInfo>> getTransaction();
}
