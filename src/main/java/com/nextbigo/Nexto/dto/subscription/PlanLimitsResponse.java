package com.nextbigo.Nexto.dto.subscription;

public record PlanLimitsResponse(

		String name,
		Integer maxProjects,
		Integer maxTokensPerDay,
		Boolean unlimitedAi
) {
}
