package fr.morgan.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.morgan.webapp.entity.Dbuser;
import fr.morgan.webapp.entity.Role;
import fr.morgan.webapp.repository.RoleRepository;
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
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.getRoles());
		return "adduser";
	}

	@PostMapping("/adduser")
	public String traitementAddUser(@ModelAttribute Dbuser user) {
		String password = pwdEncoder.encode(user.getPassword());
		user.setPassword(password);
		List<Role> roles = roleService.getDefaultRoles();
		user.setRoles(roles);
		dbuserService.addUser(user);
		return "redirect:/home";
	}

	@GetMapping("/modifyuser/{id}")
	public String modifyUser(@PathVariable(name = "id") Long id, Model model) {
		Dbuser user = dbuserService.getUser(id);
		model.addAttribute("user", user);
		return "modifyuser";

	}

	@PostMapping("/modifyuser/{id}")
	public String traitementModifyUser(@ModelAttribute Dbuser user, @PathVariable(name = "id") Long id) {
		List<Role> roles = dbuserService.getUserRoles(id);
		user.setRoles(roles);
		String pwd = user.getPassword();
		String pwdBdd = dbuserService.findPwdById(id);
		if (!pwd.equals(pwdBdd)) {
			// System.err.println("new password: " +pwd);
			pwd = pwdEncoder.encode(pwd);
			user.setPassword(pwd);
		}
		dbuserService.modifyUser(user);
		return "redirect:/user/liste";
	}

	@GetMapping("/modifyrole/{id}")
	public String modifyRole(@PathVariable("id") Long id, Model model) {
		Dbuser user = dbuserService.getUser(id);
		List<Role> allRoles = roleService.getRoles();
		model.addAttribute("user", user);
		model.addAttribute("allRoles", allRoles);
		return "modifyrole";
	}

	@PostMapping("/modifyrole/{id}")
	public String traitementModifyRole(@PathVariable("id") Long id,
			@RequestParam(name = "roles", required = false) List<String> strRoles) {

		Dbuser user = dbuserService.getUser(id);
		List<Role> roles = new ArrayList<Role>();

		if (strRoles != null) {
			for (String libelle : strRoles) {
				Role role = roleService.getRole(libelle);
				roles.add(role);
			}
		}
		user.setRoles(roles);

		System.err.println("LST STRING FROM REQUESTPARAM " + strRoles);
		System.err.println("LIST<ROLES> " + roles);
		System.err.println("USER " + user);

		dbuserService.modifyUser(user);

		return "redirect:/user/liste";
	}

}
