package com.my.commons.aspect;

public interface EntitlementService {

	boolean checkForEntitlement();

	void setUserId(String userId);

	void setResource(String resource);

	void setEntitled(boolean isEntitled);

}