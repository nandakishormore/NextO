package com.nextbigo.Nexto.entity;

import java.time.Instant;

import com.nextbigo.Nexto.enums.SubscriptionStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Subscription {
	
	Long id;
	
	User user;
	
	Plan plan;
	
	String stripeCustomerId;
	String stripeSubscriptionId;
	
	SubscriptionStatus status;
	
	Instant currentPeriodStart;
	Instant currentPeriodEnd;
	Boolean cancelAtPeriodEnd;
	
	Instant createdAt;
	Instant updatedAt;

}