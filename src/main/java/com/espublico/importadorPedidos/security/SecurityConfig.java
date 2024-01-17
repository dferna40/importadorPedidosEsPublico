package com.espublico.importadorPedidos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Indica que se activa la seguridad web en nuestra aplicacion y ademas esta
					// sera una clase la cual contendra toda la configuracion referente a la
					// seguridad
public class SecurityConfig {

	private JwtAutheticationEntryPoint jwtAutheticationEntryPoint;

	@Autowired
	public SecurityConfig(JwtAutheticationEntryPoint jwtAutheticationEntryPoint) {
		this.jwtAutheticationEntryPoint = jwtAutheticationEntryPoint;
	}

	// Se encarga de verificar la informacion de los usuarios que logean en la
	// aplicacion
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	// Se encarga de encriptar todas nuestras contraseñas
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Bean que incorporara el filtro de seguridad de jason web token de nuestra
	// clase JwtAutheticationFilter
	@Bean
	JwtAutheticationFilter jwtAutheticationFilter() {
		return new JwtAutheticationFilter();
	}

	// Establece una cadena de filtros de seguridad en nuestra aplicacion y es aqui
	// donde se determinan los permisos segun los roles de usuario segun nuestra
	// aplicacion
	@SuppressWarnings({ "removal"})
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http
			.csrf().disable() //
			.exceptionHandling() // Permitimos el manejo de excepciones
			.authenticationEntryPoint(jwtAutheticationEntryPoint) // Nos establece un punto de entrada personalizado de autenticacion para el manejo de autenticaciones no autorizadas
			.and()
			.sessionManagement() // Permite la gestion de sesiones
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Las sesiones no tendran estado
			.and()
			.authorizeHttpRequests() // Toda peticion http debe ser autorizada
			.requestMatchers("/api/auth/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
		http.addFilterBefore(jwtAutheticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
