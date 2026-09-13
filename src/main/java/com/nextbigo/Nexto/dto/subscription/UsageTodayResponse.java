package com.nextbigo.Nexto.dto.subscription;

public record UsageTodayResponse(
		Integer tokensUsed,
		Integer tokenslimit,
		Integer previewsRunning,
		Integer previewsLimit
) {
}
