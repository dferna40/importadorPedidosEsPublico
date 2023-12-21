package com.espublico.importadorPedidos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.service.ImportCsvService;

@Controller
public class ImportCsvController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImportCsvController.class);

	@Autowired
	@Qualifier("importCsvService")
	private ImportCsvService importCsvService;
	
	/**
	 * El método handleFileUpload procesa el archivo cargado y luego redirige a la
	 * página de resumen final con distintos datatables para mostrar la información de la orden.
	 * 
	 * @param Archivo CSV
	 * @return vista de resumen final
	 */
	@PostMapping("/importarPedidos")
	public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();

		if (file.isEmpty()) {
			//Si el fichero esta vacio vuelve a la pagina de inicio
			modelAndView.setViewName("index");
			modelAndView.addObject("warningMessage",
					"El archivo está vacío. Por favor seleccione un archivo para cargar.");
		} else {
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
				reader.readLine(); // Saltar encabezados

				List<String> errorMessages = importCsvService.processCsvFile(reader);
				if (!errorMessages.isEmpty()) {
					modelAndView.setViewName("errorView");
					modelAndView.addObject("errors", errorMessages);
					return modelAndView;
				}

				modelAndView.setViewName("redirect:/resumenFinal");
			} catch (IOException ioe) {
				logger.error("Error al procesar el archivo", ioe);
				modelAndView.setViewName("errorView");
				modelAndView.addObject("errorMessage",
						"Hubo un error al procesar el archivo CSV. Por favor intente nuevamente.");
			}
		}

		return modelAndView;
	}
}
