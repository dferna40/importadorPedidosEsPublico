package com.espublico.importadorPedidos.dto;

/**
 * DTO para representar un resumen final de la prioridad de las órdenes. Esta
 * clase encapsula una clave y un valor, que representan información resumida
 * sobre una prioridad de orden específica. Es útil para transferir esta
 * información de manera estructurada entre diferentes capas de una aplicación.
 */
public class OrderPriorityFinalSummaryDTO {

	private String clave;
	private Long valor;

	public OrderPriorityFinalSummaryDTO(String clave, Long valor) {
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
