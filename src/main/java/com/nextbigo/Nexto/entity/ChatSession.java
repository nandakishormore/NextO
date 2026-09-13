package com.nextbigo.Nexto.entity;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ChatSession {
	
	Project project;
	User user;
	String title;
	
	Instant createdAt;
	Instant updatedAt;
	Instant deletedAt;

}
