package com.my.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the REL_PF database table.
 * 
 */
@Embeddable
public class RelPfPK implements Serializable {
	
	//Default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REL_ID")
	private String relId;

	@Column(name="PF_ID")
	private String pfId;

	@Column(name="BRANCH_CODE")
	private long branchCode;

	public RelPfPK() {
	}
	public String getRelId() {
		return this.relId;
	}
	public void setRelId(String relId) {
		this.relId = relId;
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
		if (!(other instanceof RelPfPK)) {
			return false;
		}
		RelPfPK castOther = (RelPfPK)other;
		return 
			this.relId.equals(castOther.relId)
			&& this.pfId.equals(castOther.pfId)
			&& (this.branchCode == castOther.branchCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.relId.hashCode();
		hash = hash * prime + this.pfId.hashCode();
		hash = hash * prime + ((int) (this.branchCode ^ (this.branchCode >>> 32)));
		
		return hash;
	}
}