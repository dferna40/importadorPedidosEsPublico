package com.espublico.importadorPedidos.controller;

import com.espublico.importadorPedidos.service.IGenerateReportService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

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
				generateReportService.generateReportCsv(response, idHistory);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}
}
