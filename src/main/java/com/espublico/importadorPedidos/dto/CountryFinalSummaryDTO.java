package com.espublico.importadorPedidos.dto;

/**
 * DTO para representar el resumen final de un país.
 * Esta clase encapsula los datos clave y valor, típicamente utilizados para resumir información
 * relacionada con un país en una estructura fácil de transferir entre diferentes capas de la aplicación.
 */
public class CountryFinalSummaryDTO {
	
	private String clave;
	private Long valor;

	
	public CountryFinalSummaryDTO(String clave, Long valor) {
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
