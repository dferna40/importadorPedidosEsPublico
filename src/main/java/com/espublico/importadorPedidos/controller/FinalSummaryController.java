package com.espublico.importadorPedidos.controller;

import com.espublico.importadorPedidos.dto.FinalSummaryDTO;
import com.espublico.importadorPedidos.model.User;
import com.espublico.importadorPedidos.repository.IUserRepository;
import com.espublico.importadorPedidos.service.IFinalSummaryService;
import com.espublico.importadorPedidos.service.IGenerateReportService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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
	private IFinalSummaryService finalSummaryService;

	@Autowired
	@Qualifier("generateReportService")
	private IGenerateReportService generateReportService;

	@Autowired
	private IUserRepository userRepository;

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

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		// Necesitas obtener la entidad User relevante de tu base de datos.
		// Este es solo un ejemplo, reemplaza 'userRepository' y 'findByUsername'
		// con tu repositorio y método apropiado.
		Optional<User> optionalUser = userRepository.findByUserName(userDetails.getUsername());

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
			FinalSummaryDTO orderFinalSummaryDTO = finalSummaryService.resultFinalSummary(idHistory,optionalUser.get().getUserId());
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
