package edu.utdallas.wpl.cookies.spring.dao.integration;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@Ignore
@Transactional(readOnly=false)
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class UserInformationRepositoryIntegrationTest {
	
	@Autowired
	private UserInformationRepository userInformationRepository;
	@Autowired
	private AddressRepository addressRepository;
    
	/*@Test
	public void testCountUserInformations() {
//		assertThat(userInformationRepository.count(), greaterThan(0));
	}
	
	@Test
	public void testGetAddresses() {
//		UserInfomationEntity userInfomation = userInformationRepository.get(1);
//		
//		assertNotNull(userInfomation);
//		assertEquals(userInfomation.getId(), 1);
	}*/
	
	@Test
	public void testCreateUser() {
		
		AddressEntity address = addressRepository.get(1);
		UserInformationEntity userInformationEntity =new UserInformationEntity();
		userInformationEntity.setFirstName("Anirudh");
		userInformationEntity.setLastName("Kuttiyil Valsalan");
		userInformationEntity.setBirthDate(new Date());
		userInformationEntity.setAddress(address);
		userInformationEntity.setPassword("password");
		userInformationEntity.setEmail("ani123kv@gmail.com");
		userInformationEntity.setRegistrationDate(new Date());
		userInformationEntity.setMobileNumber("9724087043");
		userInformationEntity.setRoleId(0);
		
		userInformationEntity.setSex("M");
		userInformationEntity.setMiddleName("Kuttiyil");
		
		UserInformationEntity userInfoUser1 =userInformationRepository.get(250);
		userInfoUser1.setMiddleName("M");
		userInformationRepository.delete(userInfoUser1);
		
	}
	
	/*@Test
	public void testUpdateAddress() {
		
	}
	
	@Test
	public void testDeleteAddress() {
	
	}*/
	
}