package com.nextbigo.Nexto.dto.project;

import java.time.Instant;

import com.nextbigo.Nexto.dto.auth.UserProfileResponse;

public record ProjectResponse(
		Long id,
		String name,
		Instant createdAt,
		Instant updatedAt,
		UserProfileResponse owner
) {
}
