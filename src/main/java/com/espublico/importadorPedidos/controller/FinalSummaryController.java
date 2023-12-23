package com.espublico.importadorPedidos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.dto.FinalSummaryDTO;
import com.espublico.importadorPedidos.service.FinalSummaryService;
import com.espublico.importadorPedidos.service.GenerateReportService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FinalSummaryController {

	private static final Logger logger = LoggerFactory.getLogger(FinalSummaryController.class);

	@Autowired
	@Qualifier("finalSummaryService")
	private FinalSummaryService finalSummaryService;

	@Autowired
	@Qualifier("generateReportService")
	private GenerateReportService generateReportService;

	@GetMapping("/resumenFinal")
	public ModelAndView showFinalSummary(@RequestParam(value = "idHistorico", required = false) Long idHistoricoURL,
			ModelAndView mav, HttpSession session) {
		Long idHistory;

		// Verificar si el idHistorico viene como parámetro en la URL
		if (idHistoricoURL != null) {
			idHistory = idHistoricoURL;
			session.setAttribute("idHistory", idHistory);
		} else {
			// Si no, intentar recuperarlo de la sesión
			idHistory = (Long) session.getAttribute("idHistory");
		}

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
			// Manejar el caso donde no se encuentra el ID del historial
			mav.setViewName("error"); // o cualquier lógica que desees implementar
		}
		return mav;
	}
}
