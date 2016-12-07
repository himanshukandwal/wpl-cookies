package edu.utdallas.wpl.cookies.spring.dao.cache;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component
public class ActivePublishedBidsKeyGenerator implements KeyGenerator {
	
	@Override
    public Object generate(Object target, Method method, Object... params) {
		Long param = null;
		String key = "";
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
        if (params.length == 1) {
            if (params[0] instanceof Long) {
                param = (Long) params[0];        
                key = formatter.format(new Date(param));
                
                System.out.println("key obtained : " + key);
            }
        }
        
        return key;
	}
}