package com.espublico.importadorPedidos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity // Indica que se activa la seguridad web en nuestra aplicacion y ademas esta
					// sera una clase la cual contendra toda la configuracion referente a la
					// seguridad
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {



	// Se encarga de encriptar todas nuestras contraseñas
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Se encarga de verificar la informacion de los usuarios que logean en la
	// aplicacion
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}




	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
		accessDeniedHandler.setErrorPage("/access-denied");
		return accessDeniedHandler;
	}

	// Establece una cadena de filtros de seguridad en nuestra aplicacion y es aqui
	// donde se determinan los permisos segun los roles de usuario segun nuestra
	// aplicacion

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
				.csrf(csrf -> csrf.ignoringRequestMatchers("/chat"))
				.authorizeRequests(authorize -> authorize
						.requestMatchers("/css/**", "/js/**", "/images/**", "/registro", "/ws").permitAll()
						.anyRequest().authenticated())
				.formLogin(formLogin -> formLogin
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.successHandler(new SimpleUrlAuthenticationSuccessHandler("/inicio"))
						.permitAll())
				.exceptionHandling(exceptionHandling -> exceptionHandling
						.accessDeniedHandler(accessDeniedHandler())
				)
				.logout(logout -> logout.permitAll()
						.logoutUrl("/logout")
						.invalidateHttpSession(true)
						.clearAuthentication(true)); // Añadiendo detalles del cierre de sesión
		return http.build();
	}

}
