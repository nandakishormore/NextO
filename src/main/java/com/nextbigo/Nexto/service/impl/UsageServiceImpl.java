package com.nextbigo.Nexto.service.impl;

import org.springframework.stereotype.Service;

import com.nextbigo.Nexto.dto.subscription.PlanLimitsResponse;
import com.nextbigo.Nexto.dto.subscription.UsageTodayResponse;
import com.nextbigo.Nexto.service.UsageService;

@Service
public class UsageServiceImpl implements UsageService{

	@Override
	public UsageTodayResponse getTodaysUsageOfUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
