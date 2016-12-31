package com.my.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USER_PF database table.
 * 
 */
@Embeddable
public class UserPfPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	public UserPfPK() {
	}
}