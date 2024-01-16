package com.espublico.importadorPedidos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUserName(String userName);

	boolean existsByUserName(String userName);
}