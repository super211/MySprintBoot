package com.my.model;

import org.springframework.stereotype.Component;

@Component
public class UserIdRequest {

	String userId;
	
	String password;
	
	String newPassword;
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	String makerUserId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(String makerUserId) {
		this.makerUserId = makerUserId;
	}
	
	
}
