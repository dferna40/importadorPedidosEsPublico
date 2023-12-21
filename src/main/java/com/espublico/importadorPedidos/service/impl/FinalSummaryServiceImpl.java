package com.espublico.importadorPedidos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.dto.CountryOrderCountDTO;
import com.espublico.importadorPedidos.dto.ItemTypeOrderCountDTO;
import com.espublico.importadorPedidos.dto.FinalSummaryDTO;
import com.espublico.importadorPedidos.dto.OrderPriorityOrderCountDTO;
import com.espublico.importadorPedidos.dto.RegionOrderCountDTO;
import com.espublico.importadorPedidos.dto.SalesChannelOrderCountDTO;
import com.espublico.importadorPedidos.service.FinalSummaryService;
import com.espublico.importadorPedidos.service.PurchaseOrderService;

@Service("finalSummaryService")
public class FinalSummaryServiceImpl implements FinalSummaryService{
	
	@Autowired
	@Qualifier("purchaseOrderService")
	private PurchaseOrderService purchaseOrderService;

	@Override
	public FinalSummaryDTO resultFinalSummary(Long idHistory) {
		
		Map<String, Long> countryOrderCounts = purchaseOrderService.countPurchaseOrdersByCountryAndHistoryId(idHistory);
		Map<String, Long> regionOrderCounts = purchaseOrderService.countPurchaseOrdersByRegionAndHistoryId(idHistory);
		Map<String, Long> itemTpyeOrderCounts = purchaseOrderService.countPurchaseOrdersByItemTypeAndHistoryId(idHistory);
		Map<String, Long> orderPriorityOrderCounts = purchaseOrderService.countPurchaseOrdersByOrderPriorityAndHistoryId(idHistory);
		Map<String, Long> salesChannelOrderCounts = purchaseOrderService.countPurchaseOrdersBySalesChannelAndHistoryId(idHistory);

		List<CountryOrderCountDTO> countryOrderCountList = new ArrayList<>();
		List<RegionOrderCountDTO> regionOrderCountList = new ArrayList<>();
		List<ItemTypeOrderCountDTO> itemTpyeOrderCountList = new ArrayList<>();
		List<OrderPriorityOrderCountDTO> orderPriorityOrderCountList = new ArrayList<>();
		List<SalesChannelOrderCountDTO> salesChannelOrderCountList = new ArrayList<>();

		for (Map.Entry<String, Long> entry : countryOrderCounts.entrySet()) {
			CountryOrderCountDTO countryOrderCount = new CountryOrderCountDTO(entry.getKey(), entry.getValue());
			countryOrderCountList.add(countryOrderCount);
		}

		for (Map.Entry<String, Long> entry : regionOrderCounts.entrySet()) {
			RegionOrderCountDTO regionOrderCount = new RegionOrderCountDTO(entry.getKey(), entry.getValue());
			regionOrderCountList.add(regionOrderCount);
		}

		for (Map.Entry<String, Long> entry : itemTpyeOrderCounts.entrySet()) {
			ItemTypeOrderCountDTO itemTpyeOrderCount = new ItemTypeOrderCountDTO(entry.getKey(), entry.getValue());
			itemTpyeOrderCountList.add(itemTpyeOrderCount);
		}

		for (Map.Entry<String, Long> entry : orderPriorityOrderCounts.entrySet()) {
			OrderPriorityOrderCountDTO orderPriorityOrderCount = new OrderPriorityOrderCountDTO(entry.getKey(),
					entry.getValue());
			orderPriorityOrderCountList.add(orderPriorityOrderCount);
		}

		for (Map.Entry<String, Long> entry : salesChannelOrderCounts.entrySet()) {
			SalesChannelOrderCountDTO salesChannelOrderCount = new SalesChannelOrderCountDTO(entry.getKey(),
					entry.getValue());
			salesChannelOrderCountList.add(salesChannelOrderCount);
		}
		
		return new FinalSummaryDTO(countryOrderCountList,regionOrderCountList,itemTpyeOrderCountList,orderPriorityOrderCountList,salesChannelOrderCountList);
	}

}
