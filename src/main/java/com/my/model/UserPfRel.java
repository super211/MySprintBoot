package com.my.model;

import java.io.Serializable;

public class UserPfRel implements Serializable {

	private static final long serialVersionUID = 1L;

	// To define all the fields that needs to be shown in the result
	private String relId;

	private String userId;

	private String pfId;

	private int branchCode;

	private String refCcy;

	public UserPfRel() {

	}

	public UserPfRel(String userId) {
		this.userId = userId;
	}

	public UserPfRel(String relId, String userId, String pfId, int branchCode, String refCcy) {
		this.relId = relId;
		this.userId = userId;
		this.pfId = pfId;
		this.branchCode = branchCode;
		this.refCcy = refCcy;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPfId() {
		return pfId;
	}

	public void setPfId(String pfId) {
		this.pfId = pfId;
	}

	public int getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}

	public String getRefCcy() {
		return refCcy;
	}

	public void setRefCcy(String refCcy) {
		this.refCcy = refCcy;
	}

}
