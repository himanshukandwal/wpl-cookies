package edu.utdallas.wpl.cookies.spring.biz.integration;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.wpl.cookies.spring.biz.manager.AddressServiceManager;
import edu.utdallas.wpl.cookies.spring.biz.manager.UserInformationServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.Address;
import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;

/**
 * A sophisticated test-bed for business level testing. This includes, dozer mapping tests and application-level flow tests.
 *
 */


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testBusinessContext.xml" })
public class AddressServiceManagerIntegrationTest {

	@Autowired
	private AddressServiceManager addressServiceManager;
	
	@Autowired
	private UserInformationServiceManager userInformationServiceManager;
	
	/*@Test
	public void a_testCreateAddress() {
		Address address = addressServiceManager.createAddress(ADDRESS_1);
		
		assertNotNull(address);
		assertNotNull(address.getId());
		assertEquals(ADDRESS_1.getLine(), address.getLine());
		
		// for future handles (update event scenarios)
		ADDRESS_1.setId(address.getId());
	}*/
	
	/*@Test
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
	}*/
	
	@Test
	public void c_testGetAddress() {
	/*	System.out.println(" with out  cache>>>>>>>>>");
		Address address = addressServiceManager.getAddress(25053);
		System.out.println("address Id "+address.getId()+" city is "+address.getCity());
		System.out.println("from  cache >>>>>>>>>");
		Address address2 = addressServiceManager.getAddress(25053);
		System.out.println("address Id "+address.getId()+" city "+address2.getCity());
		address2.setCity("NewYork");
		addressServiceManager.updateAddress(address2);
		
		System.out.println("fetching updated address>>>>>>>");
		Address address4 =addressServiceManager.getAddress(25053);
		System.out.println("address Id "+address2.getId()+" city "+address4.getCity());*/
		
	System.out.println("first");
	UserInformation userInformation=userInformationServiceManager.getUserInformation(18300);
	System.out.println("second");
	UserInformation userInformation2=userInformationServiceManager.getUserInformation(18300);
	
	}
	
	/*@Test
	public void d_testGetAddresses() {
		List<Address> addresses = addressServiceManager.getAddresses();
		
		assertNotNull(addresses);
		assertThat(addresses.size(), greaterThan(0));
	}
			*/
//	@Test
//	public void e_testDeleteAddress() {
//		addressServiceManager.deleteAddress(ADDRESS_1);
//		
//		assertNull(addressServiceManager.getAddress(ADDRESS_1.getId()));
//	}
		
}