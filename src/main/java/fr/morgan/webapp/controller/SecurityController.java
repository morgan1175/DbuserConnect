package fr.morgan.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
		
	@GetMapping("/logoutsuccess")
	public String logoutSuccess() {
		return "logout";
	}
}
