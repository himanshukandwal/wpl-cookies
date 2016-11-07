package edu.utdallas.wpl.cookies.spring.services.proxy.location;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Optional;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * This class makes use of api : http://ip-api.com/docs/api:json to detect the requesting user location.
 * 
 * @author Heman
 *
 */
@Service
public class LocationLookupProxyService {

	private static final Logger LOG = LoggerFactory.getLogger(LocationLookupProxyService.class);

	@Autowired
	private LocationConfiguration config;

	@Cacheable(value = "location-metadata")
	public Optional<String> lookupLocation(String ipAddress) throws Exception {
		
		LOG.debug("Starting lookup for address {}", ipAddress);
		
		String jsonString = null;
		try {
			jsonString = getLookupContent(config.getLocationApiAddress() + (ipAddress.equals("127.0.0.1") ? "" : ipAddress));		
		} catch (HttpStatusCodeException e) { if (400 <= e.getStatusCode().value() && e.getStatusCode().value() < 500) {
				LOG.warn("Location cannot be found for address {}: {}", ipAddress, e.getMessage());
				return Optional.absent();
			} else {throw e;}
		} catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

		Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
		String country = JsonPath.read(document, "$.country");
					
		return Optional.of(country);
    }

    private String getLookupContent(String lookupAddress) throws Exception {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        HttpHeaders httpHeaders = new HttpHeaders();

        ResponseEntity<String> responseEntity = new RestTemplate(requestFactory).exchange(lookupAddress, HttpMethod.GET, new HttpEntity<String>(httpHeaders), String.class);
        return responseEntity.getBody();
    }

}
