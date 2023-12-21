package fr.morgan.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.morgan.webapp.entity.Role;
import fr.morgan.webapp.repository.RoleRepository;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}
	
	@Override
	public List<Role> getDefaultRoles() {
		List<Role> defaultRoles= new ArrayList<Role>();
		Role defaultRole = roleRepository.findByLibelle("USER");
		defaultRoles.add(defaultRole);
		return defaultRoles;
	}
	
	@Override
	public Role getRole(String libelle) {
		return roleRepository.findByLibelle(libelle);
	}
		
}
