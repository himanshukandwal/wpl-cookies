package edu.utdallas.wpl.cookies.spring.dao.integration;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.dao.orm.ApartmentEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.TransactionInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.ApartmentRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.PublishedBidsRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.TransactionInfoRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@Transactional
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class TransactionInfoRepositoryIntegrationTest {
	
	@Autowired
	private PublishedBidsRepository publishedBidsRepository;
	
	@Autowired
	private UserInformationRepository userInformationRepository;
	@Autowired
	private TransactionInfoRepository transactionInfoRepository;
   
	@Test
	public void testCreateApartment() {
		UserInformationEntity bidPoster = userInformationRepository.get(200);
		UserInformationEntity bidReceiver=userInformationRepository.get (350);
		PublishedBidsEntity publishedBidsEntity = publishedBidsRepository.get(50);
		
		
		TransactionInfoEntity transactionInfoEntity =new TransactionInfoEntity();
		transactionInfoEntity.setBid(publishedBidsEntity);
		transactionInfoEntity.setBidPoster(bidPoster);
		transactionInfoEntity.setBidReceiver(bidReceiver);
		transactionInfoEntity.setBidPrice("250");
		transactionInfoRepository.save(transactionInfoEntity);
		
	}
	
}