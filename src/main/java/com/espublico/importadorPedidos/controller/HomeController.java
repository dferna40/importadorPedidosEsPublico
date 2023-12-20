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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.service.HistoryOrderService;
import com.espublico.importadorPedidos.service.ImportCsvService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	@Qualifier("importCsvService")
	private ImportCsvService importCsvService;
	
	@Autowired
    private HistoryOrderService historyOrderService;

	@GetMapping("/inicio")
    public ModelAndView showHistory(ModelAndView mav) {
        List<HistoryOrder> historyOrders = historyOrderService.getAllHistoryOrders();
        mav.addObject("historyOrders", historyOrders);
        mav.setViewName("index");
        return mav;
    }
	
//	@GetMapping("/inicio")
//	public String index() {
//		return "index";
//	}

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

				modelAndView.setViewName("redirect:/importar");
			} catch (IOException ioe) {
				logger.error("Error al procesar el archivo", ioe);
				modelAndView.setViewName("errorView");
				modelAndView.addObject("errorMessage",
						"Hubo un error al procesar el archivo. Por favor intente nuevamente.");
			}
		}

		return modelAndView;
	}
}
