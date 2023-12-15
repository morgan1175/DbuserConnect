package fr.morgan.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.morgan.webapp.entity.Dbuser;
import fr.morgan.webapp.entity.Role;
import fr.morgan.webapp.service.DbuserService;
import fr.morgan.webapp.service.RoleService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private DbuserService dbuserService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@GetMapping("/adduser")
	public String addUser(Model model) {
		Dbuser user = new Dbuser();
		model.addAttribute("user",user);
		model.addAttribute("roles",roleService.getRoles());
		return "adduser";
	}
	
	@PostMapping("/adduser")
	public String traitementAddUser(@ModelAttribute Dbuser user) {
		String password = pwdEncoder.encode(user.getPassword());
		user.setPassword(password);
		List<Role> roles=roleService.getDefaultRoles();
		user.setRoles(roles);
		dbuserService.addUser(user);
		return "redirect:/home";
	}
	
	
	
	
}
