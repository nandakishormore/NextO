package com.nextbigo.Nexto.entity;

import java.time.Instant;

import com.nextbigo.Nexto.enums.PreviewStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Preview {
	
	Long id;
	Project project;
	String namespace;
	String podName;
	String PreviewUrl;
	PreviewStatus status;
	Instant startedAt;
	Instant terminatedAt;
	Instant createdAt;

}
