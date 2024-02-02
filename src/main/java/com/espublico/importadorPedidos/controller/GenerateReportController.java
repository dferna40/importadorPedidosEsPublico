package com.espublico.importadorPedidos.controller;

import com.espublico.importadorPedidos.model.User;
import com.espublico.importadorPedidos.repository.IUserRepository;
import com.espublico.importadorPedidos.service.IGenerateReportService;
import jakarta.servlet.http.HttpServletResponse;
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

import java.io.IOException;
import java.util.Optional;

/**
 * Controlador para manejar la generación de informes en formato CSV.
 *
 * Esta clase controladora gestiona las solicitudes para generar informes CSV. 
 * Utiliza el servicio 'generateReportService' para crear y descargar el informe basado en los datos de pedidos.
 */
@Controller
public class GenerateReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateReportController.class);

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	@Qualifier("generateReportService")
	private IGenerateReportService generateReportService;

	/**
     * Maneja la solicitud GET para generar un informe CSV.
     * 
     * Este método se activa cuando se solicita la ruta "/generarInformeCsv". Recupera el ID del historial
     * de pedidos de la sesión y, con este ID, genera un informe CSV correspondiente a ese historial.
     * El informe se envía al cliente para su descarga.
     *
     * @param response Objeto HttpServletResponse utilizado para escribir el informe CSV y controlar la respuesta HTTP.
     * @param session Sesión HTTP para obtener el ID del historial de pedidos.
     */
	@GetMapping("/generarInformeCsv")
	public void generateReportCsv(HttpServletResponse response, HttpSession session) {
		Long idHistory = (Long) session.getAttribute("idHistory");

		// Lógica con el valor recuperado
		if (idHistory != null) {
			try {
				// Obtén el usuario logueado desde Spring Security
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				// Necesitas obtener la entidad User relevante de tu base de datos.
				// Este es solo un ejemplo, reemplaza 'userRepository' y 'findByUsername'
				// con tu repositorio y método apropiado.
				Optional<User> optionalUser = userRepository.findByUserName(userDetails.getUsername());
				generateReportService.generateReportCsv(response, idHistory,optionalUser.get().getUserId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	public String getCurrentUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		} else {
			return principal.toString();
		}
	}
}
