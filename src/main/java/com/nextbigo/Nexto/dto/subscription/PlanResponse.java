package com.nextbigo.Nexto.dto.subscription;

public record PlanResponse(
		Long id,
		String name,
		Integer maxProjects,
		Integer maxTokensPerDay,
		Boolean unlimitedAi,
		Boolean active
) {
}
