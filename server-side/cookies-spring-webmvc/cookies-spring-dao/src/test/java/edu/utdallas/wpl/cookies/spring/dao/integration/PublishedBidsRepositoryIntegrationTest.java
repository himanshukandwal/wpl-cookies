package edu.utdallas.wpl.cookies.spring.dao.integration;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.common.enums.ApartmentType;
import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.ApartmentEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;
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
	
	@Autowired
	private AddressRepository addressRepository;
   
	@Test
	public void testPublishBids() {
		UserInformationEntity userInformationEntity = userInformationRepository.get(6050);
       // ApartmentEntity apartmentEntity =apartmentRepository.get(250);
        AddressEntity addressEntity =addressRepository.get(624);
         
		PublishedBidsEntity publishedBidsEntity=new PublishedBidsEntity();
		publishedBidsEntity.setActiveInd("Y");
		publishedBidsEntity.setApartmentType(ApartmentType.BHK_1.toString());
		publishedBidsEntity.setHostedDate(new Date());
		publishedBidsEntity.setComments("comments");
		publishedBidsEntity.setNumDays(30);
		publishedBidsEntity.setPrice("$300");
		publishedBidsEntity.setOwner(userInformationEntity);
		publishedBidsEntity.setFromDate(new Date());
		publishedBidsEntity.setToDate(new Date());
		publishedBidsEntity.setAddressEntity(addressEntity);
		publishedBidsRepository.save(publishedBidsEntity);
		//assertNotNull(apartmentEntity);
		
	}
	
	/*@Test
	public void getPublishBids() {
		Integer userId =5150;
		List<PublishedBidsEntity> pubLishedEntityList =publishedBidsRepository.getAllBidRequestes(userId);
		if(pubLishedEntityList!=null){
			System.out.println("size is "+pubLishedEntityList.size());
		}
		else
		{
			System.out.println("is Empty");
		}
		
		
	}*/
	
}