package com.espublico.importadorPedidos.controller;

import com.espublico.importadorPedidos.model.User;
import com.espublico.importadorPedidos.repository.IUserRepository;
import com.espublico.importadorPedidos.service.IImportCsvService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * Controlador para la gestión de importación de archivos CSV.
 * Este controlador se encarga de manejar las solicitudes relacionadas con la carga y procesamiento
 * de archivos CSV para la aplicación. Utiliza un servicio específico para realizar el procesamiento del archivo.
 */
@Controller
public class ImportCsvController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImportCsvController.class);

	@Autowired
	@Qualifier("importCsvService")
	private IImportCsvService importCsvService;

	@Autowired
	private IUserRepository userRepository;
	
	/**
	 * Maneja la carga de un archivo CSV y procesa su contenido.
	 * Este método acepta un archivo CSV cargado por el usuario, lo procesa para extraer y manejar la información
	 * y luego redirige a una vista de resumen. Si hay errores en el archivo o en el proceso de carga,
	 * redirige a una vista de error.
	 *
	 * @param file    Archivo CSV cargado por el usuario.
	 * @param session Sesión HTTP para almacenar atributos entre solicitudes.
	 * @return Objeto ModelAndView que redirige a la vista correspondiente basada en el resultado del procesamiento del archivo.
	 */
	@PostMapping("/importarPedidos")
	public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		// Tamaño máximo del archivo en bytes (200 MB)
		long maxFileSize = 100 * 1024 * 1024;

		if (file.isEmpty()) {
			modelAndView.setViewName("index");
			modelAndView.addObject("warningMessage", "El archivo está vacío.<br/> Por favor seleccione un archivo para cargar.");
		} else if (file.getSize() > maxFileSize) { // Comprobar el tamaño del archivo
			modelAndView.setViewName("index");
			modelAndView.addObject("warningMessage", "El tamaño del archivo supera el límite permitido de 100 MB.<br/>Por favor seleccione un archivo más pequeño.");
		} else {
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
				reader.readLine();
				// Obtén el usuario logueado desde Spring Security
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				// Necesitas obtener la entidad User relevante de tu base de datos.
				// Este es solo un ejemplo, reemplaza 'userRepository' y 'findByUsername'
				// con tu repositorio y método apropiado.
				Optional<User> optionalUser = userRepository.findByUserName(userDetails.getUsername());

				if (!optionalUser.isPresent()) {
					// Lanza una excepción si no se encuentra al usuario
					throw new UsernameNotFoundException("No se puede encontrar el usuario: " + userDetails.getUsername());
				}

				User user = optionalUser.get();
				List<String> errorMessages = importCsvService.processCsvFile(reader,user);
				if (!errorMessages.isEmpty()) {
					modelAndView.setViewName("errorView");
					modelAndView.addObject("errors", errorMessages);
					return modelAndView;
				}

				Long idHistory = importCsvService.getIdMaxHistoryOrder();
				if (idHistory != null) {
					session.setAttribute("idHistory", idHistory);
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
