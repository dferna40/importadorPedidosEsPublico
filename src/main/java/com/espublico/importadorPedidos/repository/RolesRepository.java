package com.espublico.importadorPedidos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.Roles;

@Repository("rolesRepository")
public interface RolesRepository extends JpaRepository<Roles, Long>{

	//Metodo para buscar el rol por el nombre en BD
	Optional<Roles> findByName(String name);
}
