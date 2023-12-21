package fr.morgan.webapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import fr.morgan.webapp.config.AuthenticationFacade;
import fr.morgan.webapp.entity.Dbuser;
import fr.morgan.webapp.entity.Role;
import fr.morgan.webapp.repository.DbuserRepository;

@Service
public class DbuserServiceImp implements DbuserService{

	@Autowired
	private DbuserRepository dbuserRepository;
	
	@Autowired
	AuthenticationFacade authenticationFacade;
			
	@Override
	public Dbuser findByName(String username) {
		return dbuserRepository.findByUsername(username);
	}

	@Override
	public List<Dbuser> getUsers() {
		return dbuserRepository.findAll();
	}

	@Override
	public Dbuser getUser(Long id) {
		return dbuserRepository.findById(id).get();
	}

	@Override
	public Dbuser addUser(Dbuser user) {
		return dbuserRepository.save(user);
	}

	@Override
	public void deleteUser(Dbuser user) {
		dbuserRepository.delete(user);
		
	}

	@Override
	public Dbuser modifyUser(Dbuser user) {
		return dbuserRepository.save(user);
	}

	@Override
	public String loginRedirectionUrl() {
		Authentication authentication=authenticationFacade.getAuthentication();
		
		//recuperation roles de l'utilisateur
		List<String> roles = authentication.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());
		
		//Affectation des url
		Map<String, String> roleToUrlMap = new HashMap<String, String>();
		roleToUrlMap.put("ROLE_ADMIN", "/admin");
		roleToUrlMap.put("ROLE_USER", "/user");
		
		//Obtention de l'url correspondant au premier role trouvé
		String defaultUrl = "/home";
		String redirectionUrl = roles.stream()
				.filter(roleToUrlMap::containsKey)
				.map(roleToUrlMap::get)
				.findFirst()
				.orElse(defaultUrl);
		return redirectionUrl;
	}

	@Override
	public List<Dbuser> filterUserByUsername(String username) {
		return dbuserRepository.findByUsernameContainingIgnoreCase(username);
	}

	@Override
	public List<Role> getUserRoles(Long id) {
		Dbuser user=dbuserRepository.findById(id).get();
		return user.getRoles();
	}

	@Override
	public String findPwdById(Long id) {
		Dbuser user=dbuserRepository.findById(id).get();
		return user.getPassword();
	}
}
