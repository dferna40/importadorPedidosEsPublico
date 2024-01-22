package com.espublico.importadorPedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.espublico.importadorPedidos.dto.LoginDTO;
import com.espublico.importadorPedidos.dto.RegisterDTO;
import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.service.impl.HistoryOrderServiceImpl;

@Controller
public class LoginRegisterController {
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private HistoryOrderServiceImpl historyOrderService;
	
	@Autowired
	private RestAuthController authController;

	@GetMapping("/login")
	public ModelAndView showLoginForm() {
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("user", new LoginDTO()); // Añadir un objeto LoginDTO con el nombre "user" al modelo
	    mav.setViewName("login");
	    return mav;
	}
	
	@PostMapping("/login")
	public String processLogin(@ModelAttribute LoginDTO loginData, Model model) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginData.getUserName(), loginData.getPassword())
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        // Redirigir a la página principal o donde corresponda
	        return "redirect:/index";
	    } catch (AuthenticationException e) {
	        // Manejar el error, posiblemente añadiendo un mensaje al modelo
	        model.addAttribute("error", "Credenciales inválidas");
	        return "login";
	    }
	}

	@GetMapping("/registro")
	public ModelAndView showRegistrationForm() {
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("user", new RegisterDTO());
	    mav.setViewName("register");
	    return mav;
	}
	
	@PostMapping("/register")
    public String processRegistration(@ModelAttribute RegisterDTO registerDTO) {
        // Aquí llamas a authService para registrar el nuevo usuario
        // Redirigir a la página de login o mostrar un mensaje de error según sea necesario
        return "redirect:/login";
    }
	
	@GetMapping("/inicio")
	public ModelAndView postLoginPage(ModelAndView mav) {
		List<HistoryOrder> historyOrders = historyOrderService.getAllHistoryOrders();
		mav.addObject("historyOrders", historyOrders);
		mav.setViewName("index");
		return mav;
	}
}
