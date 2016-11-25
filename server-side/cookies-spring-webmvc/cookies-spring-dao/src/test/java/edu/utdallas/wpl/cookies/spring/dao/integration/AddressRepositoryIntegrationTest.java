package edu.utdallas.wpl.cookies.spring.dao.integration;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.utdallas.wpl.cookies.spring.dao.orm.Car;
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
	
	
	private Car[] testCars = { new Car("Shelby American", "GT 350","1967", "This is T"),
			new Car("Chevrolet", "Bel Air","1957", "This") };
	
	/*@Test
	public void testGetAddresses() {
		AddressEntity address = addressRepository.get(1);
		
		assertNotNull(address);
		assertEquals(address.getLine1(), "7220 McCallum Blvd");
		assertEquals(address.getCountryCode(), "USA");
		assertEquals(address.getZipCode(), "75252");
	}
	*/
	/*@Test
	public void testCreateAddress() {
		
	List<AddressEntity>	 addressEntities=addressRepository.getAll();
	System.out.println("current session "+hibenateTemplate.getSessionFactory().getCurrentSession());
	System.out.println("size is "+addressEntities.size());
	for(AddressEntity addressEntity :addressEntities){
	//hibenateTemplate.getSessionFactory().getCurrentSession().save(addressEntity);
	}
	FullTextSession fullTextSession = Search.getFullTextSession(hibenateTemplate.getSessionFactory().getCurrentSession());
	
	
	
	QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(AddressEntity.class).get();
	org.apache.lucene.search.Query luceneQuery = queryBuilder.bool()
			.should(queryBuilder.keyword().onField("countryCode").matching("US").createQuery()).createQuery();

	org.hibernate.Query hibernateQuery = fullTextSession.createFullTextQuery(luceneQuery, AddressEntity.class);
	List<AddressEntity> searchResults = hibernateQuery.list();
	
	System.out.println("search result list "+searchResults.size());
		
	
	AddressEntity addressEntity =new AddressEntity();
		addressEntity.setId(2);
		addressEntity.setLine("7740 Mccallum Blvd");
	
		addressEntity.setCountryCode("DALLAS");
		addressEntity.setZipCode(75252);
		addressEntity.setCountryCode("USA");
		addressEntity.setCity("Richardson");
		addressEntity.setState("Texas");
		//addressRepository.save(addressEntity);
		
	}*/
	
	@Test
	public void testUpdateAddress() {
		
	//	Transaction tx = hibenateTemplate.getSessionFactory().getCurrentSession().beginTransaction();

		//hibenateTemplate.getSessionFactory().getCurrentSession().save(testCars[0]);
		//hibenateTemplate.getSessionFactory().getCurrentSession()
		//.save(testCars[1]);

	//	tx.commit();
		
		hibenateTemplate.getSessionFactory().getCurrentSession().save(testCars[0]);
		hibenateTemplate.getSessionFactory().getCurrentSession().save(testCars[1]);
	/*	FullTextSession fullTextSession = Search.getFullTextSession(hibenateTemplate.getSessionFactory().getCurrentSession());
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Car.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.bool()
				.should(queryBuilder.keyword().onField("model").matching("GT 350").createQuery())
				.should(queryBuilder.keyword().onField("model").matching("Bel Air").createQuery()
				).createQuery();
		org.hibernate.Query hibernateQuery = fullTextSession.createFullTextQuery(luceneQuery, Car.class);
		List<Car> searchResults = hibernateQuery.list();
		boolean foundShelby = false;
		boolean foundBelAir = false;
		System.out.println("search result >>>>>>>>>>"+searchResults.size());
		for (Car car : searchResults) {
			System.out.println("car found "+car);
			//logger.debug("Car found, id=" + car.getId() + ", model=" + car.getModel());
			if (car.getModel().equals("GT 350")) {
				foundShelby = true;
			} else if (car.getModel().equals("Bel Air")) {
				foundBelAir = true;
			}
		}
		*/
		
		
		
	}
	
	@Test
	public void testDeleteAddress() {
	
	}
	
}