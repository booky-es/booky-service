package com.booky.api.security;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.booky.api.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	@Value("${jwt.secret})")
	private String jwtSecret;
	
	public String generateJwtToken(HttpServletResponse response, User user ) {		
		Claims claims = Jwts.claims().setSubject("user");
		claims.put("id",user.getUserId());
		claims.put("firstName", user.getFirstName());
		String JWT = Jwts.builder().setSubject("email").setClaims(claims).setIssuedAt(new Date()).
				setExpiration(new Date(System.currentTimeMillis() + 1800000 ))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		return JWT;
	}
	
	public User validateJwtToken(String token) throws Exception {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			User user = new User((String)claims.get("id"),"", "", (String)claims.get("firstName"));
			return user;
		} catch(SignatureException ex) {
			LOGGER.error("Invalid JWT signature");
			throw new JwtException("Invalid JWT signature");
		} catch(MalformedJwtException ex) {
			LOGGER.error("Invalid JWT token");
			throw new JwtException("Invalid JWT token");
		} catch(ExpiredJwtException ex) {
			LOGGER.error("Expired JWT token");
			throw new JwtException("Expired JWT token");
		} catch(UnsupportedJwtException ex) {
			LOGGER.error("Unsupported JWT token");
			throw new JwtException("Unsupported JWT token");
		} catch(IllegalArgumentException ex) {
			LOGGER.error("JWT claims string is empty");
			throw new JwtException("JWT claims string is empty");
		}
	}
	
	public Authentication getAuthentication(HttpServletRequest request) throws Exception {

		if(request.getRequestURI().equalsIgnoreCase("/api/v1/login")) {
			return null;
		}
		
		String jwtToken = request.getHeader("Authorization");
				
		if(jwtToken != null && StringUtils.hasText(jwtToken) && jwtToken.startsWith("Bearer ")) {
			jwtToken = jwtToken.substring(7, jwtToken.length());			
			User loggingUuser = this.validateJwtToken(jwtToken);
			Authentication authentication = new AuthenticatedUser(loggingUuser);
			return authentication;
			
		}
		throw new ServletException("No jwt Token");
	}
}
