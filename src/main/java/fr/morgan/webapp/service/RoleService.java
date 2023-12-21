package fr.morgan.webapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.morgan.webapp.entity.Role;

@Service
public interface RoleService {

	List<Role> getRoles();
	List<Role> getDefaultRoles();
	Role getRole(String libelle);
	
	
	
}
