package com.espublico.importadorPedidos.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espublico.importadorPedidos.dto.AuthResponseDTO;
import com.espublico.importadorPedidos.dto.LoginDTO;
import com.espublico.importadorPedidos.dto.RegisterDTO;
import com.espublico.importadorPedidos.model.Roles;
import com.espublico.importadorPedidos.model.User;
import com.espublico.importadorPedidos.repository.RolesRepository;
import com.espublico.importadorPedidos.repository.UserRepository;
import com.espublico.importadorPedidos.security.JwtGenerate;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/auth/")
public class RestAuthController {

	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private RolesRepository rolesRepository;
	private UserRepository userRepository;
	private JwtGenerate jwtGenerate;

	@Autowired
	public RestAuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			RolesRepository rolesRepository, UserRepository userRepository, JwtGenerate jwtGenerate) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.rolesRepository = rolesRepository;
		this.userRepository = userRepository;
		this.jwtGenerate = jwtGenerate;
	}

	// Metodo para poder registrar usuario con roll USER
	@PostMapping("registro")
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
		if (userRepository.existsByUserName(registerDTO.getUserName())) {
			return new ResponseEntity<>("El usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setUserName(registerDTO.getUserName());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		Roles roles = rolesRepository.findByName("USER").orElseThrow(new Supplier<EntityNotFoundException>() {
			@Override
			public EntityNotFoundException get() {
				return new EntityNotFoundException("Rol no encontrado");
			}
		});
		user.setRoles(Collections.singletonList(roles));
		userRepository.save(user);

		return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.OK);
	}

	// Metodo para poder registrar usuario con roll ADMIN
	@PostMapping("registroAdmin")
	public ResponseEntity<String> registerAdmin(@RequestBody RegisterDTO registerDTO) {
		if (userRepository.existsByUserName(registerDTO.getUserName())) {
			return new ResponseEntity<>("El usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setUserName(registerDTO.getUserName());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		Roles roles = rolesRepository.findByName("ADMIN").orElseThrow(new Supplier<EntityNotFoundException>() {
			@Override
			public EntityNotFoundException get() {
				return new EntityNotFoundException("Rol no encontrado");
			}
		});
		user.setRoles(Collections.singletonList(roles));
		userRepository.save(user);

		return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.OK);
	}

	// Metodo para logear un usuario y obtener un token
	@PostMapping("login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGenerate.generateToken(authentication);
		
		return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
	}
}
