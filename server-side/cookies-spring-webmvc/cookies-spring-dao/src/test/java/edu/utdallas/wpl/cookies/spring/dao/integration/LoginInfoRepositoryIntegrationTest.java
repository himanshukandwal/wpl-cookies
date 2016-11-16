package edu.utdallas.wpl.cookies.spring.dao.integration;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.LoginInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.LoginInfoRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@Transactional(readOnly=false)
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class LoginInfoRepositoryIntegrationTest {
	
	@Autowired
	private LoginInfoRepository loginInfoRepository;
	
	@Autowired
	private UserInformationRepository userInformationRepository;
   
	@Test
	public void testCreateLogin() {
		UserInformationEntity userInformationEntity = userInformationRepository.get(200);
		
		LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
		loginInfoEntity.setUserId(userInformationEntity);
		loginInfoEntity.setDeviceName("iPhone 7");
		loginInfoEntity.setLoginTime(new Date());

		loginInfoRepository.save(loginInfoEntity);
	}
	
	
}