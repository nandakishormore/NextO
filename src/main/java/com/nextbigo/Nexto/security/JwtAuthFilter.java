package com.nextbigo.Nexto.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter{
	
	AuthUtil authUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("incoming request: {}", request.getRequestURI());
		final String requestHeaderToken = request.getHeader("Authorization");
		
		if(requestHeaderToken == null || !requestHeaderToken.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String jwtToken = requestHeaderToken.split("Bearer ")[1];
		JwtUserPrinciple user = authUtil.verifyAccessToken(jwtToken);
		
		if(user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);		
	}

}
