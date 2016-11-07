package edu.utdallas.wpl.cookies.spring.services.cachetesting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testApplicationContext.xml" })
public class CaffineSpringCacheUnitTest {

	@Autowired
	private CacheableComponent cacheableComponent;

	@Test
	public void testCache() {
		String response1 = cacheableComponent.cachedMethod("param1", "param2");
		String response2 = cacheableComponent.cachedMethod("param1", "param2");

		assertEquals(response2, response1);
	}

}