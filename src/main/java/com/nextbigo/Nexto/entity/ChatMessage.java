package com.nextbigo.Nexto.entity;

import com.nextbigo.Nexto.enums.MessageRole;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ChatMessage {
	
	Long id;
	ChatSession chatSession;
	
	String content;
	String toolcalls;
	
	Integer tokensUsed;
	Integer createdAt;
	
	MessageRole role;
}
