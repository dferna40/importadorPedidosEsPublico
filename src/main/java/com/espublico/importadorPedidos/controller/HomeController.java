package com.espublico.importadorPedidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
