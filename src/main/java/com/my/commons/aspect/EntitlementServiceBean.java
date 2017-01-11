package com.my.commons.aspect;

import org.springframework.stereotype.Service;

/*
Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
 */
@Service
public class EntitlementServiceBean implements EntitlementService {

	private String userId;
	private String resource;
	private boolean isEntitled = false;

	public boolean checkForEntitlement() {
		// The compare should be the same as the setting as "resourceName".
		// Otherwise:java.lang.RuntimeException: Security Exception
		// @EntitlementCheck(commandGroupKey = "readCommand", resourceKey =
		// "resourceName")

		/*
		 * TO-DO:if we want to check multiple resouceName, we can implement it
		 * here.
		 */
		if (getUserId().equals("bruce") && getResource().equals("resourceName")) {
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
