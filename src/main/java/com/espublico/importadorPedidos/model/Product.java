package com.espublico.importadorPedidos.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	private String itemType;

	@ManyToOne
	@JoinColumn(name = "sales_channel_id")
	private SalesChannel salesChannel;

	// Constructores, getters y setters

	public Product() {
		// Constructor vacío necesario para JPA
	}

	// Aquí puedes agregar constructores adicionales según tus necesidades

	// Getters y setters para cada campo
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public SalesChannel getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(SalesChannel salesChannel) {
		this.salesChannel = salesChannel;
	}
}
