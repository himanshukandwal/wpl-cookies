package edu.utdallas.wpl.cookies.spring.dao.integration;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;

@Transactional(readOnly=false)
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class AddressRepositoryIntegrationTest {
	
	@Autowired
	private AddressRepository addressRepository;
    
	@Test
	public void testCountAddresses() {
		assertThat(addressRepository.count(), greaterThan(0));
	}
	@Autowired
	private HibernateTemplate hibenateTemplate;
	/*@Test
	public void testGetAddresses() {
		AddressEntity address = addressRepository.get(1);
		
		assertNotNull(address);
		assertEquals(address.getLine1(), "7220 McCallum Blvd");
		assertEquals(address.getCountryCode(), "USA");
		assertEquals(address.getZipCode(), "75252");
	}
	*/
	@Test
	public void testCreateAddress() {
		
	/*List<AddressEntity>	 addressEntities=addressRepository.getAll();
	System.out.println(hibenateTemplate.getSessionFactory().getCurrentSession());
	System.out.println("size is "+addressEntities.size());*/
	//FullTextSession fullTestSession = Search.getFullTextSession(hibenateTemplate.getSessionFactory().getCurrentSession());
		
	
	/*AddressEntity addressEntity =new AddressEntity();
		addressEntity.setId(2);
		addressEntity.setLine("7740 Mccallum Blvd");
	
		addressEntity.setCountryCode("DALLAS");
		addressEntity.setZipCode(75252);
		addressEntity.setCountryCode("USA");
		addressEntity.setCity("Richardson");
		addressEntity.setState("Texas");*/
		//addressRepository.save(addressEntity);
		System.out.println("calling without cache>>>.");
		AddressEntity addressEntity =addressRepository.get(624);
		System.out.println("calling from cache>>>>>");
		AddressEntity addressEntity2 =addressRepository.get(624);
		
	}
	
	@Test
	public void testUpdateAddress() {
		
	}
	
	@Test
	public void testDeleteAddress() {
	
	}
	
}