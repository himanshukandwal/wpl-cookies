package edu.utdallas.wpl.cookies.spring.biz.integration;

import static edu.utdallas.wpl.cookies.spring.common.dto.samples.Addresses.ADDRESS_1;
import static edu.utdallas.wpl.cookies.spring.common.dto.samples.Addresses.copy;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.wpl.cookies.spring.biz.manager.AddressServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.Address;

/**
 * A sophisticated test-bed for business level testing. This includes, dozer mapping tests and application-level flow tests.
 *
 */

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testBusinessContext.xml" })
public class AddressServiceManagerIntegrationTest {

	@Autowired
	private AddressServiceManager addressServiceManager;
	
	@Test
	public void a_testCreateAddress() {
		Address address = addressServiceManager.createAddress(ADDRESS_1);
		
		assertNotNull(address);
		assertNotNull(address.getId());
		assertEquals(ADDRESS_1.getLine(), address.getLine());
		
		// for future handles (update event scenarios)
		ADDRESS_1.setId(address.getId());
	}
	
	@Test
	public void b_testUpdateAddress() {
		Address createAddressCopy = copy (ADDRESS_1);
		
		createAddressCopy.setLine(ADDRESS_1.getLine() + "-updated");
		
		Address address = addressServiceManager.updateAddress(createAddressCopy);
		
		assertNotNull(address);
		assertNotNull(address.getId());
		assertEquals(address.getLine(), containsString("-updated"));
		assertEquals(createAddressCopy.getCountryCode(), address.getCountryCode());
		
		// for future handles (find event scenarios)
		ADDRESS_1.setLine(address.getLine());
	}
	
	@Test
	public void c_testGetAddress() {
		Address address = addressServiceManager.getAddress(ADDRESS_1.getId());
		
		assertNotNull(address);
		assertNotNull(address.getId());
		assertEquals(ADDRESS_1.getLine(), address.getLine());
		assertThat(address.getLine(), containsString("-updated"));
	}
	
	@Test
	public void d_testGetAddresses() {
		List<Address> addresses = addressServiceManager.getAddresses();
		
		assertNotNull(addresses);
		assertThat(addresses.size(), greaterThan(0));
	}
			
	@Test
	public void e_testDeleteAddress() {
		addressServiceManager.deleteAddress(ADDRESS_1);
		
		assertNull(addressServiceManager.getAddress(ADDRESS_1.getId()));
	}
		
}