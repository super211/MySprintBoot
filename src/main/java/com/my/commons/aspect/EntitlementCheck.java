package com.my.commons.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface EntitlementCheck {
	public String userId(); // Required the userId

	public String commandGroupKey(); // Required for Hysterix implementation

	public String resourceKey(); // Resource which we need to check the
									// entitlement for
}
