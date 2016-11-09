package edu.utdallas.wpl.cookies.spring.dao.integration;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.wpl.cookies.spring.dao.orm.Address;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;

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
		Address address = addressRepository.get(1);
		
		assertNotNull(address);
		assertEquals(address.getLine1(), "7220 McCallum Blvd");
		assertEquals(address.getCountryCode(), "USA");
		assertEquals(address.getZipCode(), "75252");
	}
	
	@Test
	public void testCreateAddress() {
		
	}
	
	@Test
	public void testUpdateAddress() {
		
	}
	
	@Test
	public void testDeleteAddress() {
	
	}
	
}