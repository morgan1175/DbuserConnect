package fr.morgan.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.morgan.webapp.entity.Dbuser;
import fr.morgan.webapp.service.DbuserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private DbuserService dbuserService;
	
	@GetMapping("/adduser")
	public String addUser(Model model) {
		Dbuser user=new Dbuser();
		model.addAttribute(user);
		return "redirect:/home";
	}
	
}
