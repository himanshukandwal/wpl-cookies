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
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.ApartmentRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@Transactional
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class ApartmentRepositoryIntegrationTest {
	
	@Autowired
	private ApartmentRepository apartmentRepository;
	
	@Autowired
	private UserInformationRepository userInformationRepository;
   
	@Test
	public void testCreateApartment() {
		UserInformationEntity userInformationEntity = userInformationRepository.get(200);
		ApartmentEntity apartmentEntity =new ApartmentEntity();
		
		PublishedBidsEntity publishedBidsEntity =new PublishedBidsEntity();
		publishedBidsEntity.setComments("comments");
		publishedBidsEntity.setActiveInd("N");
		publishedBidsEntity.setNumDays(20);
		apartmentEntity.setApartmentName("Komal");
		apartmentEntity.setApartmentCity("Vatakara");
		apartmentEntity.setApartmentState("Kerala");
		apartmentEntity.setApartmentCountry("India");
		apartmentEntity.setOwner(userInformationEntity);
		apartmentEntity.setApartmentNumber("1088");
		apartmentEntity.setTotNumPplRated("1");
		apartmentRepository.save(apartmentEntity);
		
		assertNotNull(apartmentEntity);
		
	}
	
}