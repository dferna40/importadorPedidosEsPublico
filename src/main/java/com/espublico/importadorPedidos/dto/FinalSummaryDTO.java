package com.espublico.importadorPedidos.dto;

import java.util.List;

public class FinalSummaryDTO {

	private List<CountryOrderCountDTO> countryOrderCounts;
    private List<RegionOrderCountDTO> regionOrderCounts;
    private List<ItemTypeOrderCountDTO> itemTypeOrderCounts;
    private List<OrderPriorityOrderCountDTO> orderPriorityOrderCounts;
    private List<SalesChannelOrderCountDTO> salesChannelOrderCounts;
	
    public FinalSummaryDTO() {
		super();
	}

	public FinalSummaryDTO(List<CountryOrderCountDTO> countryOrderCounts,
			List<RegionOrderCountDTO> regionOrderCounts, List<ItemTypeOrderCountDTO> itemTypeOrderCounts,
			List<OrderPriorityOrderCountDTO> orderPriorityOrderCounts,
			List<SalesChannelOrderCountDTO> salesChannelOrderCounts) {
		super();
		this.countryOrderCounts = countryOrderCounts;
		this.regionOrderCounts = regionOrderCounts;
		this.itemTypeOrderCounts = itemTypeOrderCounts;
		this.orderPriorityOrderCounts = orderPriorityOrderCounts;
		this.salesChannelOrderCounts = salesChannelOrderCounts;
	}

	public List<CountryOrderCountDTO> getCountryOrderCounts() {
		return countryOrderCounts;
	}

	public void setCountryOrderCounts(List<CountryOrderCountDTO> countryOrderCounts) {
		this.countryOrderCounts = countryOrderCounts;
	}

	public List<RegionOrderCountDTO> getRegionOrderCounts() {
		return regionOrderCounts;
	}

	public void setRegionOrderCounts(List<RegionOrderCountDTO> regionOrderCounts) {
		this.regionOrderCounts = regionOrderCounts;
	}

	public List<ItemTypeOrderCountDTO> getItemTypeOrderCounts() {
		return itemTypeOrderCounts;
	}

	public void setItemTypeOrderCounts(List<ItemTypeOrderCountDTO> itemTypeOrderCounts) {
		this.itemTypeOrderCounts = itemTypeOrderCounts;
	}

	public List<OrderPriorityOrderCountDTO> getOrderPriorityOrderCounts() {
		return orderPriorityOrderCounts;
	}

	public void setOrderPriorityOrderCounts(List<OrderPriorityOrderCountDTO> orderPriorityOrderCounts) {
		this.orderPriorityOrderCounts = orderPriorityOrderCounts;
	}

	public List<SalesChannelOrderCountDTO> getSalesChannelOrderCounts() {
		return salesChannelOrderCounts;
	}

	public void setSalesChannelOrderCounts(List<SalesChannelOrderCountDTO> salesChannelOrderCounts) {
		this.salesChannelOrderCounts = salesChannelOrderCounts;
	}
    
    
}
