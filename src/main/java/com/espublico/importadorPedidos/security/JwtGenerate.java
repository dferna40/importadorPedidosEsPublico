package com.espublico.importadorPedidos.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JwtGenerate {

	/**
	 * Metodo para crear un token por medio de la autenticación
	 * 
	 * @param authentication
	 * @return
	 */
	public String generateToken(Authentication authentication) {

		String userName = authentication.getName();
		Date currentTime = new Date();
		Date tokenExpiration = new Date(currentTime.getTime() + SecurityConstants.JWT_EXPIRATION_TOKEN);

		// Se genera el token
		String token = Jwts.builder() // Construimos un token JWT llamado token
				.setSubject(userName) // Establecemos el nombre de usuario que esta iniciando sesion
				.setIssuedAt(new Date()) // Establecemos la fecha de emision del token en el momento actual
				.setExpiration(tokenExpiration) // Establecemos la fecha de caducidad de token
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SIGNATURE) // Utilizamos este metodo para
																						// firmar nuestro token y de
																						// esta forma evitar la
																						// manipulacion o modificacion
																						// de este

				.compact(); // Este metodo finaliza la construccion del token y lo convierte en una cadena
							// compacta

		return token;
	}

	/**
	 * Metodo para extraer un UserName a partir de un token
	 * 
	 * @param token
	 * @return
	 */
	public String getUserNameFromJWT(String token) {

		Claims claims = Jwts.parser() // El metodo parser se utiliza con el fin de analizar el token
				.setSigningKey(SecurityConstants.JWT_SIGNATURE) // Establece la clave de firma, que se utiliza para
																// verificar la firma del token
				.parseClaimsJws(token) // Se utiliza para verificar la firma del token, a partir de String token
				.getBody(); // Obtenemos el claims(cuerpo) ya verificado del token el cual contendra la
							// informacion de nombre de usuario, fecha de expiracion y firma del token

		return claims.getSubject(); // devolvemos el nombre de usuario
	}

	/**
	 * Metodo para validar el token
	 * 
	 * @param token
	 * @return
	 */
	public Boolean validateToken(String token) {
		try {
			// Validacion del token por medio de la firma que contiene el string
			// token(token) si son identicas validara el token o caso contrario saltara la
			// excepcion de abajo
			Jwts.parser().setSigningKey(SecurityConstants.JWT_SIGNATURE).parseClaimsJws(token);
			return Boolean.TRUE;
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("Jwt ha expirado o esta incorrecto");
		}
	}
}
