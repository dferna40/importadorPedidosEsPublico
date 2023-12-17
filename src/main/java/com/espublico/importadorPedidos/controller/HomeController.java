package com.espublico.importadorPedidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/inicio")
    public String index() {
        return "index";  // Sin la extensión .html
    }
	
	@GetMapping("/importar")
    public String about() {
        return "about";  // Sin la extensión .html
    }
	
	@GetMapping("/resumen")
    public String resumen() {
        return "about";  // Sin la extensión .html
    }
}
