package edu.utdallas.wpl.cookies.spring.services.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import edu.utdallas.wpl.cookies.spring.dao.integration.AbstractEmbeddedMongoIntegrationTest;

@WebAppConfiguration
@ContextConfiguration({ "classpath:testApplicationContext.xml" })
public class AbstractContextControllerTests extends AbstractEmbeddedMongoIntegrationTest {

	@Autowired
	protected WebApplicationContext wac;

}
