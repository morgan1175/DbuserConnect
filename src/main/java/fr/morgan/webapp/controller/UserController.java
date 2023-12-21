package fr.morgan.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.morgan.webapp.entity.Dbuser;
import fr.morgan.webapp.service.DbuserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private DbuserService dbuserService;

	@GetMapping("/liste")
	public String getUsers(Model model) {
		model.addAttribute("liste", dbuserService.getUsers());
		return "users";
	}

	@GetMapping("/liste/filterusers{username}")
	public String filterUsers(@RequestParam("username") String username, Model model) {
		List<Dbuser> filteredUsers = dbuserService.filterUserByUsername(username);
		if (filteredUsers.size() == 0) {
			filteredUsers = dbuserService.getUsers();
		}
		model.addAttribute("liste", filteredUsers);
		return "users";
	}
	
	
}
