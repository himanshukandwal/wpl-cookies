package edu.utdallas.wpl.cookies.spring.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.utdallas.wpl.cookies.spring.biz.manager.TransactionServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;
import edu.utdallas.wpl.cookies.spring.services.TransactionInfoRestService;

@Controller
@RequestMapping("/api")
public class TransactionInfoRestServiceImpl implements TransactionInfoRestService {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionInfoRestService.class);
	@Autowired

	private TransactionServiceManager transactionManager;

	@Override
	@RequestMapping(value = "/getTransactions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionInfo>> getTransaction() {

		List<TransactionInfo> transactionInfoList = transactionManager.getTranscation();
		if (transactionInfoList != null) {
			LOG.info(" The number of bid requests for user  :" + transactionInfoList.size());
			return ResponseEntity.ok(transactionInfoList);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

}
