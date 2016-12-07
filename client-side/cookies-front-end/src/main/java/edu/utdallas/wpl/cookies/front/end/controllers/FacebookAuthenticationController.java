package edu.utdallas.wpl.cookies.front.end.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FacebookAuthenticationController {

	@RequestMapping(value = "/auth/facebook/callback")
	public Map<String, Object> callback() {
		System.out.println("successfully logged in!");
		return new HashMap<>();
	}

}