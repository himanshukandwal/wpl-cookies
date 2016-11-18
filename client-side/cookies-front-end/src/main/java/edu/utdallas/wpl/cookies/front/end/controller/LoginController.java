package edu.utdallas.wpl.cookies.front.end.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping("/welcome")
	public ModelAndView	welcome() {
		return new ModelAndView("hello", "message", "Himanshu and Anirudha");
	}
	
}
