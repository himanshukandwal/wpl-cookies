package edu.utdallas.wpl.cookies.spring.services.cachetesting;

import java.util.Random;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheableComponent {

	@Cacheable(value="default")
	public String cachedMethod(String param1, String param2) {
		return "response " + new Random().nextInt();
	}
		
}
