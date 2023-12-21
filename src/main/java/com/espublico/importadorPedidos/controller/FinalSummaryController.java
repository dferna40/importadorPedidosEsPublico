package com.espublico.importadorPedidos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.dto.CountryOrderCountDTO;
import com.espublico.importadorPedidos.dto.ItemTypeOrderCountDTO;
import com.espublico.importadorPedidos.dto.OrderPriorityOrderCountDTO;
import com.espublico.importadorPedidos.dto.RegionOrderCountDTO;
import com.espublico.importadorPedidos.dto.SalesChannelOrderCountDTO;
import com.espublico.importadorPedidos.service.PurchaseOrderService;

@Controller
public class FinalSummaryController {

	private static final Logger logger = LoggerFactory.getLogger(FinalSummaryController.class);

	@Autowired
	@Qualifier("purchaseOrderService")
	private PurchaseOrderService purchaseOrderService;

	@GetMapping("/resumenFinal")
	public ModelAndView showFinalSummary(ModelAndView mav) {
		Map<String, Long> countryOrderCounts = purchaseOrderService.countPurchaseOrdersByCountry();
		Map<String, Long> regionOrderCounts = purchaseOrderService.countPurchaseOrdersByRegion();
		Map<String, Long> itemTpyeOrderCounts = purchaseOrderService.countPurchaseOrdersByItemType();
		Map<String, Long> orderPriorityOrderCounts = purchaseOrderService.countPurchaseOrdersByOrderPriority();
		Map<String, Long> salesChannelOrderCounts = purchaseOrderService.countPurchaseOrdersBySalesChannel();

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
			OrderPriorityOrderCountDTO orderPriorityOrderCount = new OrderPriorityOrderCountDTO(entry.getKey(), entry.getValue());
			orderPriorityOrderCountList.add(orderPriorityOrderCount);
		}
		
		for (Map.Entry<String, Long> entry : salesChannelOrderCounts.entrySet()) {
			SalesChannelOrderCountDTO salesChannelOrderCount = new SalesChannelOrderCountDTO(entry.getKey(), entry.getValue());
			salesChannelOrderCountList.add(salesChannelOrderCount);
		}
		
		mav.addObject("countryOrderCountList", countryOrderCountList);
		mav.addObject("regionOrderCountList", regionOrderCountList);
		mav.addObject("itemTpyeOrderCountList", itemTpyeOrderCountList);
		mav.addObject("orderPriorityOrderCountList", orderPriorityOrderCountList);
		mav.addObject("salesChannelOrderCountList", salesChannelOrderCountList);
		mav.setViewName("finalSummary");
		return mav;
	}
}
