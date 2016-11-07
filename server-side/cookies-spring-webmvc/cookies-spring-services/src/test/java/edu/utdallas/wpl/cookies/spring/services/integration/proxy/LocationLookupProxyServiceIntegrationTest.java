package edu.utdallas.wpl.cookies.spring.services.integration.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Optional;

import edu.utdallas.wpl.cookies.spring.services.proxy.location.LocationLookupProxyService;

/**
 * Created by hkandwa on 7/20/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testApplicationContext.xml" })
public class LocationLookupProxyServiceIntegrationTest {

    @Autowired
    private LocationLookupProxyService locationLookupProxyService;

    @Test
    public void testLocalLookupLocation() throws Exception {
    	Optional<String> countryLocationOptional = locationLookupProxyService.lookupLocation("127.0.0.1");

        assertTrue(countryLocationOptional.isPresent());
        assertEquals("United States", countryLocationOptional.get());
    }

}