package com.espublico.importadorPedidos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.service.ImportCsvService;

@Controller
public class HomeController {
	
	@Autowired
	@Qualifier("importCsvService")
	private ImportCsvService importCsvService;

	@GetMapping("/inicio")
	public String index() {
		return "index";
	}

	@GetMapping("/importar")
	public String about() {
		return "about";
	}

	@GetMapping("/resumen")
	public String resumen() {
		return "about";
	}

	/**
	 * El método handleFileUpload procesa el archivo cargado y luego redirige a una
	 * página con un datatable para mostrar la información de la orden.
	 * 
	 * @param Archivo CSV
	 * @return vista de información
	 */
	@PostMapping("/importarPedidos")
	public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
				// Saltar la primera línea que contiene los encabezados
				reader.readLine();
				

				List<String> errorMessages = importCsvService.processCsvFile(reader);
				if (!errorMessages.isEmpty()) {
					// Manejar los mensajes de error
					ModelAndView modelAndView = new ModelAndView("errorView");
		            modelAndView.addObject("errors", errorMessages);
		            return modelAndView;
				}
				// Después de procesar el archivo, redirigir a la página con información
				return new ModelAndView("redirect:/importar");
			} catch (IOException ioe) {
				// Manejar excepciones
				ioe.printStackTrace();
			}
		}
		// Redirigir a la página de carga si hay un error o el archivo está vacío
		return new ModelAndView("redirect:/inicio");
	}
}
