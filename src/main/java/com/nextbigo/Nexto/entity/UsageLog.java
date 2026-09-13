package com.nextbigo.Nexto.entity;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UsageLog {
	
	Long id;
	User user;
	Project project;
	
	String action;
	Integer tokensUsed;
	Integer durationMs;
	
	String metadata;
	Instant createdAt;
}
