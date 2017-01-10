package com.my.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

/**
 * The persistent class for the USER_PF database table.
 * 
 */
@Entity
@Table(name = "USER_PF")
@NamedQuery(name = "UserPf.findAll", query = "SELECT u FROM UserPf u")
@SqlResultSetMappings(@SqlResultSetMapping(name = "UserPfRel", classes = {
		@ConstructorResult(targetClass = UserPfRel.class, columns = {
				@ColumnResult(name = "REL_ID", type = String.class),
				@ColumnResult(name = "USER_ID", type = String.class),
				@ColumnResult(name = "PF_ID", type = String.class),
				@ColumnResult(name = "BRANCH_CODE", type = Integer.class),
				@ColumnResult(name = "REF_CCY", type = String.class) }) }))
@NamedNativeQueries({
		@NamedNativeQuery(name = "UserPf.findUserPortRelIds", query = "SELECT A.REL_ID,A.USER_ID,B.PF_ID, B.BRANCH_CODE,C.REF_CCY FROM USER_REL A JOIN REL_PF B JOIN PF_DETAILS C ON C.PF_ID = B.PF_ID ON A.REL_ID = B.REL_ID AND A.USER_ID = :USER_ID", resultSetMapping = "UserPfRel"),
		@NamedNativeQuery(name = "UserPf.findUserPortIds", query = "SELECT '' REL_ID, A.*, '' REF_CCY FROM USER_PF A WHERE A.USER_ID = :USER_ID", resultSetMapping = "UserPfRel") })
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