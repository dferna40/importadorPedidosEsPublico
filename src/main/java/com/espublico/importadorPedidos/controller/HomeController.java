package com.espublico.importadorPedidos.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

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
	 * El método handleFileUpload procesa el archivo cargado y luego redirige a una página con un datatable para mostrar la información de la orden.
	 * @param Archivo CSV
	 * @return vista de información
	 */
    @PostMapping("/importarPedidos")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
    	// Lógica para procesar el archivo
    	if (!file.isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
                // Saltar la primera línea que contiene los encabezados
                reader.readLine();

                String line;
                while ((line = reader.readLine()) != null) {
                    // Procesar cada línea del archivo CSV
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    while (tokenizer.hasMoreTokens()) {
                        String token = tokenizer.nextToken();
                        // Aquí, 'token' contiene cada valor separado por comas de la línea actual
                        // Implementa la lógica para procesar estos valores
                    }
                }
                // Después de procesar el archivo, redirigir a la página con información
                return new ModelAndView("redirect:/importar");
            } catch (Exception e) {
                // Manejar excepciones
                e.printStackTrace();
            }
        }
        // Redirigir a la página de carga si hay un error o el archivo está vacío
        return new ModelAndView("redirect:/inicio");
    }
}
