package com.espublico.importadorPedidos.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa el historial de órdenes en la base de datos. Esta
 * clase mapea a la tabla 'order_history' y almacena la información histórica de
 * las órdenes de compra, incluyendo un identificador único, la fecha de cambio
 * y una relación con las órdenes de compra asociadas.
 */
@Entity
@Table(name = "order_history")
public class HistoryOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_id")
	private Long historyId;

	@OneToMany(mappedBy = "historyOrder")
	private Set<PurchaseOrder> purchaseOrders;

	@Column(name = "change_date")
	private LocalDateTime changeDate;

	public HistoryOrder() {
		// Constructor vacío necesario para JPA
	}

	public HistoryOrder(Long historyId) {
		this.historyId = historyId;
	}

	public HistoryOrder(Long historyId, Set<PurchaseOrder> purchaseOrders, LocalDateTime changeDate) {
		super();
		this.historyId = historyId;
		this.purchaseOrders = purchaseOrders;
		this.changeDate = changeDate;
	}

	// Getters y setters
	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public LocalDateTime getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDateTime changeDate) {
		this.changeDate = changeDate;
	}

	public Set<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

}
