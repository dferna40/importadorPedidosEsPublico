package com.espublico.importadorPedidos.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
	        mav.addObject("countryOrderCounts", countryOrderCounts);
	        mav.addObject("regionOrderCounts", regionOrderCounts);
	        mav.addObject("itemTpyeOrderCounts", itemTpyeOrderCounts);
	        mav.addObject("orderPriorityOrderCounts", orderPriorityOrderCounts);
	        mav.addObject("salesChannelOrderCounts", salesChannelOrderCounts);
	        mav.setViewName("finalSummary");
	        return mav;
	    }
}
