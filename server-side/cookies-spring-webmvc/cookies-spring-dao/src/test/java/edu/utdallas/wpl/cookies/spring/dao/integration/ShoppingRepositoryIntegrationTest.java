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
import edu.utdallas.wpl.cookies.spring.dao.orm.ShoppingInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.TransactionInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.ShoppingRepository;
import edu.utdallas.wpl.cookies.spring.dao.repository.TransactionInfoRepository;

@Transactional
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class ShoppingRepositoryIntegrationTest {
	
	@Autowired
	private ShoppingRepository shoppingRepository;
    
	@Autowired
	private TransactionInfoRepository transactionInfoRepository;
	/*@Test
	public void testCountAddresses() {
		assertThat(shoppingRepository.count(), greaterThan(0));
	}
	*/
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
		
    ShoppingInfoEntity shoppingEntity =new ShoppingInfoEntity();
    TransactionInfoEntity transactionInfoEntity= transactionInfoRepository.get(7200);
    shoppingEntity.setTransactionInfo(transactionInfoEntity);
    shoppingEntity.setPrice(100f);
    shoppingEntity.setQuantity(10);
    shoppingRepository.save(shoppingEntity);
	
		
	}
	
	/*@Test
	public void testUpdateAddress() {
		
	}
	
	@Test
	public void testDeleteAddress() {
	
	}*/
	
}