package com.espublico.importadorPedidos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.model.PurchaseOrder;
import com.espublico.importadorPedidos.service.PurchaseOrderService;



@Controller
public class FinalSummaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(FinalSummaryController.class);
	
	@Autowired
	@Qualifier("purchaseOrderService")
	private PurchaseOrderService purchaseOrderService;

	 @GetMapping("/resumenFinal")
	    public ModelAndView showFinalSummary(ModelAndView mav) {
	        List<PurchaseOrder> purchaseOrders = purchaseOrderService.findAllOrders();
	        mav.addObject("purchaseOrders", purchaseOrders);
	        mav.setViewName("finalSummary");
	        return mav;
	    }
}
