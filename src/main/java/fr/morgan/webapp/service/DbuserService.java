package fr.morgan.webapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.morgan.webapp.entity.Dbuser;
import fr.morgan.webapp.entity.Role;

@Service
public interface DbuserService {

	List<Dbuser> getUsers();
	List<Dbuser> filterUserByUsername(String username);
	List<Role> getUserRoles(Long id);
	Dbuser getUser(Long id);
	Dbuser addUser(Dbuser user);
	void deleteUser(Dbuser user);
	Dbuser modifyUser(Dbuser user);
	Dbuser findByName(String username);
	String loginRedirectionUrl();
	String findPwdById(Long id);
	
}
