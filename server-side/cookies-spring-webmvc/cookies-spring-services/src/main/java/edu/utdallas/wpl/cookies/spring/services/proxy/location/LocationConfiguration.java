package edu.utdallas.wpl.cookies.spring.services.proxy.location;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * location api configuration.
 * 
 * @author Heman
 *
 */
@Component
public class LocationConfiguration {

    @Value("${locationapi.baseurl}")
	private String locationApiAddress;

    @Value("${location.lookup.cache.expiry.time}")
    private int locationLookupCacheExpiryTime;

    @Value("${location.lookup.cache.size.max}")
    private int locationLookupCacheSizeMax;

	public String getLocationApiAddress() {
		return locationApiAddress;
	}

	public void setLocationApiAddress(String locationApiAddress) {
		this.locationApiAddress = locationApiAddress;
	}

	public int getLocationLookupCacheExpiryTime() {
		return locationLookupCacheExpiryTime;
	}

	public void setLocationLookupCacheExpiryTime(int locationLookupCacheExpiryTime) {
		this.locationLookupCacheExpiryTime = locationLookupCacheExpiryTime;
	}

	public int getLocationLookupCacheSizeMax() {
		return locationLookupCacheSizeMax;
	}

	public void setLocationLookupCacheSizeMax(int locationLookupCacheSizeMax) {
		this.locationLookupCacheSizeMax = locationLookupCacheSizeMax;
	}
	
}