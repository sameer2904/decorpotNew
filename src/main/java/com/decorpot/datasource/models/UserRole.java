package com.decorpot.datasource.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserRole {

	@Id
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
	private User user;

	@Column(name = "ROLE_NAME")
	private String roleName;
}
