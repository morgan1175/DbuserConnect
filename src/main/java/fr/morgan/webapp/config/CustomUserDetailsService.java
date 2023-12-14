package fr.morgan.webapp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.morgan.webapp.entity.Dbuser;
import fr.morgan.webapp.entity.Role;
import fr.morgan.webapp.repository.DbuserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	DbuserRepository dbuserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Dbuser user = dbuserRepository.findByUsername(username);
		return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRoles()));
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getLibelle())));
		
		//authorities.forEach(auth -> System.err.println("AUTHORITY: "+ auth.getAuthority()));
		
		return authorities;
	}}
