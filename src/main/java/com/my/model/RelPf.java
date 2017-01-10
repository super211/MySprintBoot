package com.my.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the REL_PF database table.
 * 
 */
@Entity
@Table(name = "REL_PF")
@NamedQuery(name = "RelPf.findAll", query = "SELECT r FROM RelPf r")
public class RelPf implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RelPfPK id;

	public RelPf() {
	}

	public RelPfPK getId() {
		return this.id;
	}

	public void setId(RelPfPK id) {
		this.id = id;
	}

}