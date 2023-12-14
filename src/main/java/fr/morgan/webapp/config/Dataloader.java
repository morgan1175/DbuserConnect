//package fr.morgan.webapp.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import fr.morgan.webapp.entity.Dbuser;
//import fr.morgan.webapp.entity.Role;
//import fr.morgan.webapp.repository.DbuserRepository;
//import fr.morgan.webapp.repository.RoleRepository;
//
////@Component
//public class Dataloader implements CommandLineRunner {
//
//	@Autowired
//	private DbuserRepository dbuserRepository;
//	
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Override
//	public void run(String... args) throws Exception {
//		
////		Role adminRole;
////		Role userRole;
//		
////		 Verification que la bdd soit vide et ajout de l'admin
////		if (dbuserRepository.count() == 0) {
////			adminRole = createRoleIfNotFound("ADMIN");
////			userRole = createRoleIfNotFound("USER");
////		} else {
////			adminRole = roleRepository.findByLibelle("ADMIN");
////			userRole = roleRepository.findByLibelle("USER");
////			}
//			
//			Dbuser admin = new Dbuser();
//			admin.setUsername("admin");
//			admin.setPassword(passwordEncoder.encode("admin"));
////			admin.setRoles(Arrays.asList(adminRole, userRole));
//			
//			Dbuser user = new Dbuser();
//			user.setUsername("user");
//			user.setPassword(passwordEncoder.encode("user"));
////			user.setRoles(Collections.singletonList(userRole));
//			
//			dbuserRepository.save(admin);
//			dbuserRepository.save(user);
//			
//		}
//
//	
//	
//	private Role createRoleIfNotFound(String roleName) {
//	    Role role = roleRepository.findByLibelle(roleName);
//	    if (role == null) {
//	        role = new Role();
//	        role.setLibelle(roleName);
//	        roleRepository.save(role);
//	    }
//	    return role;
//	}
//
//	private Role createRole(String roleLibelle) {
//		Role role = roleRepository.findByLibelle(roleLibelle);
//		return role;
//	}
//}
