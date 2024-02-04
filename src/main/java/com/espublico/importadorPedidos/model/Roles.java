package com.espublico.importadorPedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "role")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id")
	private Long rolId;

	@NotNull
	@Size(min = 1, max = 6)
	@Column(name = "name")
	private String name;

	public Long getRolId() {
		return rolId;
	}

	public void setUserRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
