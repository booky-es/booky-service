package com.booky.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Authentication Filter to validate token for all requests to the resources
 *
 */
public class AuthenticationFilter extends OncePerRequestFilter {

	private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

	private Set<String> excludeUrls = new HashSet<>(Arrays.asList("/v2/api-docs", "/confiugraitons/**",
			"/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/h2-console/**",
			"/api/v1/login",
			"/r/*"));
	private AntPathMatcher pathMatcher = new AntPathMatcher();

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		LOGGER.info("AuthenticationFilter : doFilterInternal : Begin");
		
		try {
			Authentication authentication = jwtTokenProvider.getAuthentication(request);
	        SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch(Exception ex) {
			LOGGER.error("Exception in AuthenticationFilter : "+ ex.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not resolve jwt token");
			return;
		}
		
		LOGGER.info("AuthenticationFilter : doFilterInternal : End");
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return excludeUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));

	}

}
