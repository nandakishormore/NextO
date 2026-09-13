package com.nextbigo.Nexto.dto.auth;

public record AuthResponse(
		String token, 
		UserProfileResponse response
) {
}
