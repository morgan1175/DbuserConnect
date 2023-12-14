package fr.morgan.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.morgan.webapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

	public Role findByLibelle(String libelle);
}
