package com.nextbigo.Nexto.dto.member;

import java.time.Instant;

import com.nextbigo.Nexto.enums.ProjectRole;

public record MemberResponse(
		Long id,
		String username,
		String name,
		ProjectRole projectRole,
		Instant invitedAt
) {
}
