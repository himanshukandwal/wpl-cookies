package edu.utdallas.wpl.cookies.spring.dao.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.dao.orm.LoginInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.LoginInfoEntityPk;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.LoginInfoRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;

@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginInfoRepositoryIntegrationTest {
	
	@Autowired
	private LoginInfoRepository loginInfoRepository;
	
	@Autowired
	private UserInformationRepository userInformationRepository;
   
	private static LoginInfoEntity temporaryLoginInfo;
	
	@Test
	public void a_testCreateLogin() {
		UserInformationEntity userInformationEntity = userInformationRepository.get(200);
		
		LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
		loginInfoEntity.setUser(userInformationEntity);
		loginInfoEntity.setDeviceName("iPhone 7");
		loginInfoEntity.setLoginTime(new Date());
		loginInfoEntity.setSessionId("1234ABCD4567");
		loginInfoEntity = loginInfoRepository.save(loginInfoEntity);
		
		assertNotNull(loginInfoEntity);
		assertEquals("iPhone 7", loginInfoEntity.getDeviceName());
		assertEquals("1234ABCD4567", loginInfoEntity.getSessionId());
		
		temporaryLoginInfo = loginInfoEntity;
	}
	
	@Test
	public void b_testUpdateLogin() {	
		temporaryLoginInfo.setDeviceName("iPhone 7s");
		temporaryLoginInfo.setLoginTime(new Date());
		temporaryLoginInfo.setSessionId("1234ABCD45679");
		loginInfoRepository.update(temporaryLoginInfo);
		
		assertNotNull(temporaryLoginInfo);
		assertEquals("iPhone 7s", temporaryLoginInfo.getDeviceName());
		assertEquals("1234ABCD45679", temporaryLoginInfo.getSessionId());
	}	
	
	@Test
	public void c_testUpdateLogin() {	
		loginInfoRepository.delete(temporaryLoginInfo);
		
		temporaryLoginInfo = loginInfoRepository.get(new LoginInfoEntityPk(temporaryLoginInfo.getUser(), temporaryLoginInfo.getDeviceName()));
		
		assertNull("LoginInfo not found !", temporaryLoginInfo);
	}
	
}