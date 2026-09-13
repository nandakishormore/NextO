package com.nextbigo.Nexto.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException{
	String resourceName;
	String resourceId;
}
