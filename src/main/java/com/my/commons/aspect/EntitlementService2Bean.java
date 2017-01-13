package com.my.commons.aspect;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value="EntitlementService2Bean")
public class EntitlementService2Bean implements EntitlementService {

	private String userId;
	private String resource;
	private boolean isEntitled = false;

	public boolean checkForEntitlement() {
		// The compare should be the same as the setting as "resourceName2".
		// Otherwise:java.lang.RuntimeException: Security Exception
		// @EntitlementCheck(commandGroupKey = "readCommand", resourceKey =
		// "resourceName2")
		if (getResource().equals("resourceName2")) {
			isEntitled = true;
		}
		return isEntitled;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public boolean isEntitled() {
		return isEntitled;
	}

	public void setEntitled(boolean isEntitled) {
		this.isEntitled = isEntitled;
	}

}
