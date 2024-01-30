package com.espublico.importadorPedidos.repository;

import com.espublico.importadorPedidos.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("rolesRepository")
public interface IRolesRepository extends JpaRepository<Roles, Long>{

	//Metodo para buscar el rol por el nombre en BD
	Optional<Roles> findByName(String name);
}
