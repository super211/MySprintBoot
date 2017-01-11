package com.my.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the USER_REL database table.
 * 
 */
@Entity
@Table(name = "USER_REL")
@NamedQuery(name = "UserRel.findAll", query = "SELECT u FROM UserRel u")
public class UserRel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REL_ID")
	private String relId;

	@Column(name = "USER_ID")
	private String userId;

	public UserRel() {
	}

	public String getRelId() {
		return this.relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}