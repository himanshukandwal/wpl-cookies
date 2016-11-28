package edu.utdallas.wpl.cookies.spring.dao.integration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.common.enums.ApartmentType;
import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.ApartmentRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.PublishedBidsRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@Transactional(readOnly=false)
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
		
		System.out.println(ApartmentType.BHK_1.getApartment());
		publishedBidsEntity.setApartmentType(ApartmentType.BHK_1.getApartment());
		publishedBidsEntity.setHostedDate(new Date());
		publishedBidsEntity.setComments(" timestamp");
		publishedBidsEntity.setNumDays(30);
		publishedBidsEntity.setPrice(300.00f);
		publishedBidsEntity.setOwner(userInformationEntity);
		publishedBidsEntity.setFromDate(new Date());
		publishedBidsEntity.setToDate(new Date());
		publishedBidsEntity.setModifiedDate(new Date(Calendar.getInstance().getTimeInMillis()));
		publishedBidsEntity.setAddressEntity(addressEntity);
		//publishedBidsRepository.save(publishedBidsEntity);
		//assertNotNull(apartmentEntity);
		List<PublishedBidsEntity> pubList=	publishedBidsRepository.getAllActiveBids(1480287976419L);
		System.out.println("Size "+pubList.size());
		
		/*SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a");
		System.out.println("formated >"+dateFormat.format(new Date(1480287976419L)));
		
		System.out.println("Date is "+new Date(1480287976419L));*/
		
	}
	
	/*@Test
	public void getPublishBids() {
		Integer userId =6050;
		List<PublishedBidsEntity> pubLishedEntityList =publishedBidsRepository.getAllBidRequestes(userId);
		if(pubLishedEntityList!=null){
			System.out.println("size is "+pubLishedEntityList.size());
		}
		else
		{
			System.out.println("is Empty");
		}
		
		
	}
*/
	
	
	/*@Test
	public void getPublishBids() {
		///Integer userId =6050;
		List<PublishedBidsEntity> pubLishedEntityList =publishedBidsRepository.getAll();
		if(pubLishedEntityList!=null){
			System.out.println("size is "+pubLishedEntityList.size());
		}
		else
		{
			System.out.println("is Empty");
		}
		
		
	}*/
}