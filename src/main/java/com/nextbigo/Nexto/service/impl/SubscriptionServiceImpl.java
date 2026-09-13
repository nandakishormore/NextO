package com.nextbigo.Nexto.service.impl;

import org.springframework.stereotype.Service;

import com.nextbigo.Nexto.dto.subscription.CheckoutRequest;
import com.nextbigo.Nexto.dto.subscription.CheckoutResponse;
import com.nextbigo.Nexto.dto.subscription.PortalResponse;
import com.nextbigo.Nexto.dto.subscription.SubscriptionResponse;
import com.nextbigo.Nexto.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

	@Override
	public SubscriptionResponse getCurrentSubscription(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckoutResponse createCheckoutSessionUrl(Long userId, CheckoutRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PortalResponse openCustomerPortal(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
