package edu.utdallas.wpl.cookies.spring.dao.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class UserInformationRepositoryIntegrationTest {
	
	@Autowired
	private UserInformationRepository userInformationRepository;
    
	@Test
	public void testCountUserInformations() {
//		assertThat(userInformationRepository.count(), greaterThan(0));
	}
	
	@Test
	public void testGetAddresses() {
//		UserInfomation userInfomation = userInformationRepository.get(1);
//		
//		assertNotNull(userInfomation);
//		assertEquals(userInfomation.getId(), 1);
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