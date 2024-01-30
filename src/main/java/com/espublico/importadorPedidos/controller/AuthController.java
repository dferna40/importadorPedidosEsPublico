package com.espublico.importadorPedidos.controller;

import com.espublico.importadorPedidos.dto.LoginDTO;
import com.espublico.importadorPedidos.dto.RegisterDTO;
import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.model.User;
import com.espublico.importadorPedidos.repository.IUserRepository;
import com.espublico.importadorPedidos.service.IRegistrationService;
import com.espublico.importadorPedidos.service.impl.HistoryOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {

	@Autowired
	private HistoryOrderServiceImpl historyOrderService;

	@Autowired
    private IRegistrationService registrationService;

	@Autowired
	private IUserRepository userRepository;


	private AuthenticationManager authenticationManager;
	@GetMapping("/login")
	public ModelAndView showLoginForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", new LoginDTO()); // Añadir un objeto LoginDTO con el nombre "user" al modelo
		mav.setViewName("login");
		return mav;
	}

	@GetMapping("/registro")
	public ModelAndView showRegistrationForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", new RegisterDTO());
		mav.setViewName("register");
		return mav;
	}

	@PostMapping("/registro")
	public String processRegistration(@ModelAttribute RegisterDTO registerDTO) {
		registrationService.register(registerDTO);
		// Redirigir a la página de login o mostrar un mensaje de error según sea necesario
		return "redirect:/login";
	}

	@GetMapping("/inicio")
	public ModelAndView index(ModelAndView mav) {
		List<HistoryOrder> historyOrders = historyOrderService.getAllHistoryOrders();
		mav.addObject("historyOrders", historyOrders);
		mav.addObject("usuarioLogin",getCurrentUsername());
		mav.addObject("email",getCurrentUserEmail());
		mav.setViewName("index");
		return mav;
	}

	public String getCurrentUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	public String getCurrentUserEmail() {
		String username = getCurrentUsername();
		Optional<User> optionalUser = userRepository.findByUserName(username);

		return optionalUser.map(User::getEmail).orElse(null);
	}
}