package com.espublico.importadorPedidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_rol")
public class UserRol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_rol_id")
	private Long userRolId;
	
	@Column(name = "name")
	private String name;

	public Long getUserRolId() {
		return userRolId;
	}

	public void setUserRolId(Long userRolId) {
		this.userRolId = userRolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
