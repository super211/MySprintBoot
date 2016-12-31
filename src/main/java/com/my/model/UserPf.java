package com.my.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the USER_PF database table.
 * 
 */
@Entity
@Table(name = "USER_PF")
@NamedQuery(name = "UserPf.findAll", query = "SELECT u FROM UserPf u")
public class UserPf implements Serializable {
	private static final long serialVersionUID = 1L;

	public UserPf() {
	}

	public UserPf(String userId) {

	}

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "PF_ID")
	private String pfId;

	@Column(name = "BRANCH_CODE")
	private long branchCode;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPfId() {
		return this.pfId;
	}

	public void setPfId(String pfId) {
		this.pfId = pfId;
	}

	public long getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(long branchCode) {
		this.branchCode = branchCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserPfPK)) {
			return false;
		}
		UserPf castOther = (UserPf) other;
		return this.userId.equals(castOther.userId) && this.pfId.equals(castOther.pfId)
				&& (this.branchCode == castOther.branchCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.pfId.hashCode();
		hash = hash * prime + ((int) (this.branchCode ^ (this.branchCode >>> 32)));

		return hash;
	}
}