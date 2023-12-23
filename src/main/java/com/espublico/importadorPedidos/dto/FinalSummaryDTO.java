package com.espublico.importadorPedidos.dto;

import java.util.List;

/**
 * DTO para representar un resumen final compuesto. Esta clase encapsula
 * múltiples listas de resúmenes por país, región, tipo de ítem, prioridad de
 * orden y canal de ventas. Sirve como un contenedor agregado para transportar
 * estos datos agrupados en diferentes categorías.
 */
public class FinalSummaryDTO {

	private List<CountryFinalSummaryDTO> countryFinalSummary;
	private List<RegionFinalSummaryDTO> regionFinalSummary;
	private List<ItemTypeFinalSummaryDTO> itemTypeFinalSummary;
	private List<OrderPriorityFinalSummaryDTO> orderPriorityFinalSummary;
	private List<SalesChannelFinalSummaryDTO> salesChannelFinalSummary;

	public FinalSummaryDTO() {
		super();
	}

	public FinalSummaryDTO(List<CountryFinalSummaryDTO> countryFinalSummary,
			List<RegionFinalSummaryDTO> regionFinalSummary, List<ItemTypeFinalSummaryDTO> itemTypeFinalSummary,
			List<OrderPriorityFinalSummaryDTO> orderPriorityFinalSummary,
			List<SalesChannelFinalSummaryDTO> salesChannelFinalSummary) {
		super();
		this.countryFinalSummary = countryFinalSummary;
		this.regionFinalSummary = regionFinalSummary;
		this.itemTypeFinalSummary = itemTypeFinalSummary;
		this.orderPriorityFinalSummary = orderPriorityFinalSummary;
		this.salesChannelFinalSummary = salesChannelFinalSummary;
	}

	public List<CountryFinalSummaryDTO> getCountryFinalSummary() {
		return countryFinalSummary;
	}

	public void setCountryFinalSummary(List<CountryFinalSummaryDTO> countryFinalSummary) {
		this.countryFinalSummary = countryFinalSummary;
	}

	public List<RegionFinalSummaryDTO> getRegionFinalSummary() {
		return regionFinalSummary;
	}

	public void setRegionFinalSummary(List<RegionFinalSummaryDTO> regionFinalSummary) {
		this.regionFinalSummary = regionFinalSummary;
	}

	public List<ItemTypeFinalSummaryDTO> getItemTypeFinalSummary() {
		return itemTypeFinalSummary;
	}

	public void setItemTypeFinalSummary(List<ItemTypeFinalSummaryDTO> itemTypeFinalSummary) {
		this.itemTypeFinalSummary = itemTypeFinalSummary;
	}

	public List<OrderPriorityFinalSummaryDTO> getOrderPriorityFinalSummary() {
		return orderPriorityFinalSummary;
	}

	public void setOrderPriorityFinalSummary(List<OrderPriorityFinalSummaryDTO> orderPriorityFinalSummary) {
		this.orderPriorityFinalSummary = orderPriorityFinalSummary;
	}

	public List<SalesChannelFinalSummaryDTO> getSalesChannelFinalSummary() {
		return salesChannelFinalSummary;
	}

	public void setSalesChannelFinalSummary(List<SalesChannelFinalSummaryDTO> salesChannelFinalSummary) {
		this.salesChannelFinalSummary = salesChannelFinalSummary;
	}
}
