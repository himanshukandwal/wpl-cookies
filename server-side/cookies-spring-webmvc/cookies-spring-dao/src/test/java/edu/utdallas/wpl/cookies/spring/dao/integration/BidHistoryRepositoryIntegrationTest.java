package edu.utdallas.wpl.cookies.spring.dao.integration;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.ApartmentEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.BidHistoryEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.ApartmentRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.BidHistoryRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.PublishedBidsRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@Transactional(readOnly=false)
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class BidHistoryRepositoryIntegrationTest {
	
	@Autowired
	private BidHistoryRepository bidHistoryRepository;
	@Autowired
	private ApartmentRepository apartmentRepository;
    @Autowired
    private PublishedBidsRepository publishedBidsRepository;
    
    @Autowired
    private UserInformationRepository userInformationRepository;
	
	
	
	
	@Test
	public void testCreateBidHistory() {
		ApartmentEntity apartmentEntity =apartmentRepository.get(250);
		PublishedBidsEntity publishedBidsEntity =publishedBidsRepository.get(50);
		UserInformationEntity userInformationEntity =userInformationRepository.get(300);
		
		BidHistoryEntity bidHistoryEntity =new BidHistoryEntity();
		bidHistoryEntity.setApartmentId(apartmentEntity);
		bidHistoryEntity.setBid(publishedBidsEntity);
		bidHistoryEntity.setBidPrice("$100");
		bidHistoryEntity.setBidStatus("OPEN");
		bidHistoryEntity.setUser(userInformationEntity);
		bidHistoryRepository.save(bidHistoryEntity);
		
	}
	
	@Test
	public void testUpdateAddress() {
		
	}
	
	@Test
	public void testDeleteAddress() {
	
	}
	
}