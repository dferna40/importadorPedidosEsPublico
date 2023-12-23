package com.espublico.importadorPedidos.dto;

import java.time.LocalDate;

public class PurchaseOrderDTO {

	private Long purchaseOrderId;
	private Long orderId;
	private LocalDate orderDate;
	private LocalDate shipDate;
	private String orderPriority;
	private Integer unitsSold;
	private String salesChannel;
	private String country;
	private String itemType;
	private String region;
	private double unitPrice;
	private double unitCost;
	private double totalRevenue;
	private double totalCost;
	private double totalProfit;
	private Long historyId;

	// Constructores, getters y setters

	public PurchaseOrderDTO() {
		// Constructor vacío
	}

	public PurchaseOrderDTO(Long purchaseOrderId, Long orderId, LocalDate orderDate, LocalDate shipDate,
			String orderPriority, Integer unitsSold, String salesChannel, String country, String itemType,
			String region, double unitPrice, double unitCost, double totalRevenue, double totalCost, double totalProfit,
			Long historyId) {
		super();
		this.purchaseOrderId = purchaseOrderId;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.orderPriority = orderPriority;
		this.unitsSold = unitsSold;
		this.salesChannel = salesChannel;
		this.country = country;
		this.itemType = itemType;
		this.region = region;
		this.unitPrice = unitPrice;
		this.unitCost = unitCost;
		this.totalRevenue = totalRevenue;
		this.totalCost = totalCost;
		this.totalProfit = totalProfit;
		this.historyId = historyId;
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

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

}
