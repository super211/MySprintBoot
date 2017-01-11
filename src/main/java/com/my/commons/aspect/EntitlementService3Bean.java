package com.my.commons.aspect;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
// After experiment, @Primary has to be put in 3rd, cannot in 2nd, or 1st.
// Otherwise the 1st and 2nd bean cannot be created, only the 3rd works
// If we use @Qualifier in controller, then we need to use @ComponentScan.
public class EntitlementService3Bean implements EntitlementService {

	private String userId;

	private String resource;
	private boolean isEntitled = false;

	public boolean checkForEntitlement() {
		// The compare should be the same as the setting as "resourceName2".
		// Otherwise:java.lang.RuntimeException: Security Exception
		// @EntitlementCheck(commandGroupKey = "readCommand", resourceKey =
		// "resourceName2")
		if (getResource().equals("resourceName3")) {
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
