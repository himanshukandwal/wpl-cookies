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
	
	@Test
	public void testGetAddresses() {
		AddressEntity address = addressRepository.get(1);
		
		assertNotNull(address);
		assertEquals(address.getLine1(), "7220 McCallum Blvd");
		assertEquals(address.getCountryCode(), "USA");
		assertEquals(address.getZipCode(), "75252");
	}
	
	@Test
	public void testCreateAddress() {
		
		AddressEntity addressEntity =new AddressEntity();
		addressEntity.setId(2);
		addressEntity.setLine1("7740 Mccallum Blvd");
		addressEntity.setLine2("Apt 2083");
		addressEntity.setCountryCode("DALLAS");
		addressEntity.setZipCode("75252");
		addressRepository.save(addressEntity);
		
	}
	
	@Test
	public void testUpdateAddress() {
		
	}
	
	@Test
	public void testDeleteAddress() {
	
	}
	
}