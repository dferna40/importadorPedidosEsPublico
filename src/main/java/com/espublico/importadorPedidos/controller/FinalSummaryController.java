package com.espublico.importadorPedidos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.dto.FinalSummaryDTO;
import com.espublico.importadorPedidos.service.FinalSummaryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FinalSummaryController {

	private static final Logger logger = LoggerFactory.getLogger(FinalSummaryController.class);

	@Autowired
	@Qualifier("finalSummaryService")
	private FinalSummaryService finalSummaryService;

	@GetMapping("/resumenFinal")
	public ModelAndView showFinalSummary(ModelAndView mav, HttpSession session) {
		// Recuperar el id de historico de la sesión
		Long idHistory = (Long) session.getAttribute("idHistory");

		// Lógica con el valor recuperado
		if (idHistory != null) {
			FinalSummaryDTO orderFinalSummaryDTO = finalSummaryService.resultFinalSummary(idHistory);

			mav.addObject("countryOrderCountList", orderFinalSummaryDTO.getCountryOrderCounts());
			mav.addObject("regionOrderCountList", orderFinalSummaryDTO.getRegionOrderCounts());
			mav.addObject("itemTpyeOrderCountList", orderFinalSummaryDTO.getItemTypeOrderCounts());
			mav.addObject("orderPriorityOrderCountList", orderFinalSummaryDTO.getOrderPriorityOrderCounts());
			mav.addObject("salesChannelOrderCountList", orderFinalSummaryDTO.getSalesChannelOrderCounts());
			mav.setViewName("finalSummary");
		} else {
			System.out.println("");
		}
		return mav;
	}
}
