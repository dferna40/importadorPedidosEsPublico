package com.espublico.importadorPedidos.dto;

/**
 * DTO para representar un resumen final de un tipo de ítem. Esta clase
 * encapsula una clave y un valor, que juntos representan información resumida
 * sobre un tipo de ítem específico. Es útil para transferir esta información de
 * manera estructurada entre diferentes capas de una aplicación.
 */
public class ItemTypeFinalSummaryDTO {

	private String clave;
	private Long valor;

	public ItemTypeFinalSummaryDTO(String clave, Long valor) {
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
