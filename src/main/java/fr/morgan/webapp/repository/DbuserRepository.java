package fr.morgan.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.morgan.webapp.entity.Dbuser;

public interface DbuserRepository extends JpaRepository<Dbuser, Long> {

	public Dbuser findByUsername(String username);
	public List<Dbuser> findByUsernameContainingIgnoreCase(String username);
}
