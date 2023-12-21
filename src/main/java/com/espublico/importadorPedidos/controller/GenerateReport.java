package com.espublico.importadorPedidos.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.espublico.importadorPedidos.service.GenerateReportService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class GenerateReport {

	@Autowired
	@Qualifier("generateReportService")
	private GenerateReportService generateReportService;

	@GetMapping("/generarInformeCsv")
	public void generateReportCsv(HttpServletResponse response, HttpSession session) {

		// Recuperar el objeto de la sesión
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
