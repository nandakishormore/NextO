package com.nextbigo.Nexto.service;

import com.nextbigo.Nexto.dto.subscription.CheckoutRequest;
import com.nextbigo.Nexto.dto.subscription.CheckoutResponse;
import com.nextbigo.Nexto.dto.subscription.PortalResponse;
import com.nextbigo.Nexto.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {

	SubscriptionResponse getCurrentSubscription(Long userId);

	CheckoutResponse createCheckoutSessionUrl(Long userId, CheckoutRequest request);

	PortalResponse openCustomerPortal(Long userId);

}
