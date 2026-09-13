package com.nextbigo.Nexto.service;

import com.nextbigo.Nexto.dto.subscription.PlanLimitsResponse;
import com.nextbigo.Nexto.dto.subscription.UsageTodayResponse;

public interface UsageService {

	UsageTodayResponse getTodaysUsageOfUser(Long userId);

	PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);

}
