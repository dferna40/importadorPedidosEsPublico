package com.espublico.importadorPedidos.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_order_id")
	private Long purchaseOrderId;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@Column(name = "ship_date")
	private LocalDate shipDate;

	@Column(name = "order_priority")
	private String orderPriority;

	@Column(name = "units_sold")
	private Integer unitsSold;

	@Column(name = "sales_channel")
	private String salesChannel;

	@Column(name = "country")
	private String country;

	@Column(name = "item_type")
	private String itemType;

	@Column(name = "region")
	private String region;

	@Column(name = "unit_price")
	private double unitPrice;

	@Column(name = "unit_cost")
	private double unitCost;

	@Column(name = "total_revenue")
	private double totalRevenue;

	@Column(name = "total_cost")
	private double totalCost;

	@Column(name = "total_profit")
	private double totalProfit;
	
	@ManyToOne
    @JoinColumn(name = "history_id")
	private HistoryOrder historyOrder;

	// Constructor sin parametros Getters y setters
	public PurchaseOrder() {
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
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

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public HistoryOrder getHistoryOrder() {
		return historyOrder;
	}

	public void setHistoryOrder(HistoryOrder historyOrder) {
		this.historyOrder = historyOrder;
	}
}
