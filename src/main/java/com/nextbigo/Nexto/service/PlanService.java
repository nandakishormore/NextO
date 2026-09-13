package com.nextbigo.Nexto.service;

import java.util.List;

import com.nextbigo.Nexto.dto.subscription.PlanResponse;

public interface PlanService {

	List<PlanResponse> getAllActivePlans();

}
