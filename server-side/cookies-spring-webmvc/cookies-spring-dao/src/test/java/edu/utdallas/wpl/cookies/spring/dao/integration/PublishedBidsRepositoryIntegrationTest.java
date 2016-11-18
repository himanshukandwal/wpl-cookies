package edu.utdallas.wpl.cookies.spring.dao.integration;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.dao.orm.ApartmentEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.ApartmentRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.PublishedBidsRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@Transactional
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class PublishedBidsRepositoryIntegrationTest {
	
	@Autowired
	private ApartmentRepository apartmentRepository;
	
	@Autowired
	private UserInformationRepository userInformationRepository;
	@Autowired
	private PublishedBidsRepository publishedBidsRepository;
   
	@Test
	public void testPublishBids() {
		UserInformationEntity userInformationEntity = userInformationRepository.get(200);
        ApartmentEntity apartmentEntity =apartmentRepository.get(250);

		PublishedBidsEntity publishedBidsEntity=new PublishedBidsEntity();
		publishedBidsEntity.setActiveInd("Y");
		publishedBidsEntity.setApartment(apartmentEntity);
		publishedBidsEntity.setHostedDate(new Date());
		publishedBidsEntity.setComments("comments");
		publishedBidsEntity.setNumDays(30);
		publishedBidsEntity.setPrice("$300");
		publishedBidsEntity.setUserId(userInformationEntity);
		publishedBidsRepository.save(publishedBidsEntity);
		assertNotNull(apartmentEntity);
		
	}
	
}