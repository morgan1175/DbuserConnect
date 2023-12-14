package fr.morgan.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.morgan.webapp.service.DbuserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private DbuserService dbuserService;
	
	@GetMapping("/liste")
	public String getUsers(Model model) {
		model.addAttribute("liste",dbuserService.getUsers());
		return "users";
	}
}
