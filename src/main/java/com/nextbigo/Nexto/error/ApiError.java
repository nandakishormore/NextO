package com.nextbigo.Nexto.error;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
public record ApiError(
	HttpStatus httpStatus,
	String message,
	Instant timestamp,
	@JsonInclude(JsonInclude.Include.NON_NULL) List<ApiFieldError> errors
) {
	public ApiError( HttpStatus httpStatus, String message ) {
		this(httpStatus, message, Instant.now(), null);
	}
	public ApiError( HttpStatus httpStatus, String message, List<ApiFieldError> errors) {
		this(httpStatus, message, Instant.now(), errors);
	}
}
