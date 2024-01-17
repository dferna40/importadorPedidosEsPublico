package com.espublico.importadorPedidos.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * La funcion de esta clase sera validar la informacion del token y si esto es
 * exitoso, establecera la autenticacion de un usuario en la solicitud o en el contexto de seguridad de nuestra aplicacion que se
 * hace desde postman
 */
public class JwtAutheticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUsersDetailsService customUsersDetailsService;

	@Autowired
	private JwtGenerate jwtGenerate;

	public String getRequestToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getRequestToken(request);
		
		if(StringUtils.hasText(token) && jwtGenerate.validateToken(token)) {
			String userName = jwtGenerate.getUserNameFromJWT(token);
			UserDetails userDetails = customUsersDetailsService.loadUserByUsername(userName);
			List<String> userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
			if(userRoles.contains("USER") || userRoles.contains("ADMIN")) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
