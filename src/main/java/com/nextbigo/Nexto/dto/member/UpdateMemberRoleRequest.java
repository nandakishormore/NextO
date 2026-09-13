package com.nextbigo.Nexto.dto.member;

import com.nextbigo.Nexto.enums.ProjectRole;

import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
		@NotNull ProjectRole role
) {
}
