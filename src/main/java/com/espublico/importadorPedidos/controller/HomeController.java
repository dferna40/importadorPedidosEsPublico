package com.espublico.importadorPedidos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.service.HistoryOrderService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	
	@Autowired
    private HistoryOrderService historyOrderService;

	@GetMapping({"/", "/inicio"})
    public ModelAndView showHistoryOrder(ModelAndView mav) {
        List<HistoryOrder> historyOrders = historyOrderService.getAllHistoryOrders();
        mav.addObject("historyOrders", historyOrders);
        mav.setViewName("index");
        return mav;
    }
	

	@GetMapping("/importar")
	public String finalSummary() {
		return "finalSummary";
	}

	@GetMapping("/resumen")
	public String resumen() {
		return "about";
	}

	
}
