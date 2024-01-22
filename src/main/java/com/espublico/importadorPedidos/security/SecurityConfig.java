package com.espublico.importadorPedidos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
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
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAutheticationEntryPoint))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/auth/login", "/api/auth/registro").permitAll()
	            .requestMatchers(HttpMethod.GET,"/login","/registro").permitAll()
	            .requestMatchers(HttpMethod.POST, "api/auth/registro", "/auth/registroAdmin").permitAll()
	            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Permite acceso a recursos estáticos
	            .anyRequest().authenticated())
	        .formLogin(form -> form
	            .loginPage("/login")
	            .loginProcessingUrl("/login")
	            .successHandler(new SimpleUrlAuthenticationSuccessHandler("/inicio")))
	        .httpBasic();

	    // Asegúrate de que `addFilterBefore` se llama directamente sobre `http`
	    http.addFilterBefore(jwtAutheticationFilter(), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}


}
