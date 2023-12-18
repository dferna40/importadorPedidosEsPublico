package com.espublico.importadorPedidos.dto;

import java.time.LocalDate;

public class OrderDTO {

	private Long orderId;
	private LocalDate orderDate;
	private LocalDate shipDate;
	private String orderPriority;
	private Integer unitsSold;
	private Long productId;
	private String productName; // Ejemplo, si quieres incluir detalles del producto
	private Long customerId;
	private String customerName; // Ejemplo, si quieres incluir detalles del cliente

	// Constructores, getters y setters

	public OrderDTO() {
		// Constructor vacío
	}

	// Constructor con todos los campos
	public OrderDTO(Long orderId, LocalDate orderDate, LocalDate shipDate, String orderPriority, Integer unitsSold,
			Long productId, String productName, Long customerId, String customerName) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.orderPriority = orderPriority;
		this.unitsSold = unitsSold;
		this.productId = productId;
		this.productName = productName;
		this.customerId = customerId;
		this.customerName = customerName;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getShipDate() {
		return shipDate;
	}

	public void setShipDate(LocalDate shipDate) {
		this.shipDate = shipDate;
	}

	public String getOrderPriority() {
		return orderPriority;
	}

	public void setOrderPriority(String orderPriority) {
		this.orderPriority = orderPriority;
	}

	public Integer getUnitsSold() {
		return unitsSold;
	}

	public void setUnitsSold(Integer unitsSold) {
		this.unitsSold = unitsSold;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
