package com.nextbigo.Nexto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextbigo.Nexto.dto.subscription.PlanLimitsResponse;
import com.nextbigo.Nexto.dto.subscription.UsageTodayResponse;
import com.nextbigo.Nexto.service.UsageService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usage/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsageController {
	
	UsageService usageService;
	
	@GetMapping("/today")
	public ResponseEntity<UsageTodayResponse> getTodaysUsage(){
		Long userId = 1l;
		return ResponseEntity.ok(usageService.getTodaysUsageOfUser(userId));
	}
	
	@GetMapping("/limits")
	public ResponseEntity<PlanLimitsResponse> getPlanLimits(){
		Long userId = 1l;
		return ResponseEntity.ok(usageService.getCurrentSubscriptionLimitsOfUser(userId));
	}
	
}
