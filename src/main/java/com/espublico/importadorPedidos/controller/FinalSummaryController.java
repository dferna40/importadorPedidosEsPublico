package com.espublico.importadorPedidos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.espublico.importadorPedidos.service.PurchaseOrderService;



@Controller
public class FinalSummaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(FinalSummaryController.class);
	
	@Autowired
	@Qualifier("purchaseOrderService")
	private PurchaseOrderService purchaseOrderService;

//	 @GetMapping("/resumenFinal")
//	    public String showHistory(ModelAndView mav) {
//	        List<HistoryOrder> historyOrders = purchaseOrderService.findAllOrders();
//	        mav.addObject("historyOrders", historyOrders);
//	        mav.setViewName("index");
//	        return "historyView"; // Reemplaza con el nombre de tu vista
//	    }
}
