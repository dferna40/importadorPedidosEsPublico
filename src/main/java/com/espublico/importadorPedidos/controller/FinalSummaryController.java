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

/**
 * Controlador para manejar las solicitudes relacionadas con el resumen final de pedidos.
 * 
 * Esta clase controladora maneja las solicitudes para ver el resumen final de los pedidos importados.
 * Utiliza servicios específicos para obtener y procesar los datos necesarios para mostrar este resumen.
 */
@Controller
public class FinalSummaryController {

	private static final Logger logger = LoggerFactory.getLogger(FinalSummaryController.class);

	@Autowired
	@Qualifier("finalSummaryService")
	private FinalSummaryService finalSummaryService;

	@Autowired
	@Qualifier("generateReportService")
	private GenerateReportService generateReportService;

	/**
     * Maneja la solicitud GET para mostrar el resumen final de los pedidos.
     * 
     * Este método gestiona las solicitudes a la ruta "/resumenFinal". Recupera el ID de historial
     * de la sesión o de un parámetro de URL y, con este ID, consulta el resumen final de los pedidos
     * a través del servicio correspondiente. Luego, pasa estos datos a la vista para su presentación.
     *
     * @param idHistoricoURL El ID del historial de importación recibido como parámetro en la URL.
     * @param mav Objeto ModelAndView para definir el modelo y la vista.
     * @param session Sesión HTTP para almacenar y recuperar atributos como el ID del historial.
     * @return ModelAndView El objeto ModelAndView con los datos del resumen final y la vista configurada.
     */
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
			mav.addObject("countryOrderCountList", orderFinalSummaryDTO.getCountryFinalSummary());
			mav.addObject("regionOrderCountList", orderFinalSummaryDTO.getRegionFinalSummary());
			mav.addObject("itemTpyeOrderCountList", orderFinalSummaryDTO.getItemTypeFinalSummary());
			mav.addObject("orderPriorityOrderCountList", orderFinalSummaryDTO.getOrderPriorityFinalSummary());
			mav.addObject("salesChannelOrderCountList", orderFinalSummaryDTO.getSalesChannelFinalSummary());
			mav.setViewName("finalSummary");
		} else {
			// Manejar el caso donde no se encuentra el ID del historial
			mav.setViewName("error"); // o cualquier lógica que desees implementar
		}
		return mav;
	}
}
