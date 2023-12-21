package com.espublico.importadorPedidos.dto;

public class ItemTypeOrderCountDTO {
	
	private String clave;
	private Long valor;

	
	public ItemTypeOrderCountDTO(String clave, Long valor) {
		super();
		this.clave = clave;
		this.valor = valor;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public Long getValor() {
		return valor;
	}


	public void setValor(Long valor) {
		this.valor = valor;
	}

}
