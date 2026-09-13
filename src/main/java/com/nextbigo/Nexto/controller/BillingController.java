package com.nextbigo.Nexto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextbigo.Nexto.dto.subscription.CheckoutRequest;
import com.nextbigo.Nexto.dto.subscription.CheckoutResponse;
import com.nextbigo.Nexto.dto.subscription.PlanResponse;
import com.nextbigo.Nexto.dto.subscription.PortalResponse;
import com.nextbigo.Nexto.dto.subscription.SubscriptionResponse;
import com.nextbigo.Nexto.security.AuthUtil;
import com.nextbigo.Nexto.service.PlanService;
import com.nextbigo.Nexto.service.SubscriptionService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillingController {
	
	PlanService planService;
	SubscriptionService subscriptionService;
	AuthUtil authUtil;
	
	@GetMapping("/api/plans")
	public ResponseEntity<List<PlanResponse>> getAllPlans(){
		return ResponseEntity.ok(planService.getAllActivePlans());
	}

	@GetMapping("/api/me/subcription")
	public ResponseEntity<SubscriptionResponse> getMySubscription(){
		Long userId = authUtil.getCurrentUserId();
		return ResponseEntity.ok(subscriptionService.getCurrentSubscription(userId));
	}
	
	@PostMapping("/api/stripe/checkout")
	public ResponseEntity<CheckoutResponse> createCheckoutResponse(@RequestBody @Valid CheckoutRequest request){
		Long userId = authUtil.getCurrentUserId();
		return ResponseEntity.ok(subscriptionService.createCheckoutSessionUrl(userId, request));
	}
	
	@PostMapping("/api/stripe/portal")
	public ResponseEntity<PortalResponse> openCustomerPortal(){
		Long userId = authUtil.getCurrentUserId();
		return ResponseEntity.ok(subscriptionService.openCustomerPortal(userId));
	}
	
}
