package com.nextbigo.Nexto.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nextbigo.Nexto.Repository.UserRepository;
import com.nextbigo.Nexto.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthUtil {

    UserRepository userRepository;

	@Value("${jwt.secret-key}")
	String jwtSecretKey;
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateAccessToken(User user) {	
		return Jwts.builder()
				.subject(user.getUsername())
				.claim("userId", user.getId().toString())
				.issuedAt(Date.from(Instant.now()))
				.expiration(new Date(System.currentTimeMillis() + 1000*60*10))
				.signWith(getSecretKey())
				.compact();
	}
	
	public JwtUserPrinciple verifyAccessToken(String token) {
		Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
		
		Long userId = Long.parseLong(claims.get("userId",String.class));
		String username = claims.getSubject();
		
		return new JwtUserPrinciple(userId, username);
	}
	
	public Long getCurrentUserId() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if ( authentication == null || !(authentication.getPrincipal() instanceof JwtUserPrinciple)) {
			throw new AuthenticationCredentialsNotFoundException( "Invalid request! Please login/Signup first!" );
		}
		
		String principal = authentication.getPrincipal().toString();	
		User user = userRepository.findByUsername(principal)
				.orElseThrow(() -> new AuthenticationCredentialsNotFoundException( "Invalid request! Please login/Signup first!" ));
		
		return user.getId();
	}
	
}
