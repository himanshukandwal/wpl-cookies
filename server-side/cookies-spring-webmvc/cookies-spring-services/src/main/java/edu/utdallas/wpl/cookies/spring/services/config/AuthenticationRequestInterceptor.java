package edu.utdallas.wpl.cookies.spring.services.config;

import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationRequestInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationRequestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("Intercepted request for authentication !");
		
		boolean result = false;
		if (request.getHeader("token") != null) {
			String decodedToken = new String(Base64.getDecoder().decode(request.getHeader("token")));
			
			String token = decodedToken.split(",")[0];
			long timestamp = Long.valueOf(decodedToken.split(",")[1]);
			
			if (token.equals("cookies") && new Date(timestamp).after(new Date()))
				result = true;
			else
				System.out.println("token expired, It was issued on " + new Date(timestamp));
		}
		
		System.out.println("authenticated as " + result);
		return result;
	}

}
