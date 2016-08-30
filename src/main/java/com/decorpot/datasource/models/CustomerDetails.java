package com.decorpot.datasource.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class CustomerDetails {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "CUSTOMER_NAME")
	private String custName;
	
	@Column(name = "BHK_TYPE")
	private String bhk;
	
	@Column(name = "PHONE")
	private String phone;
	
	@OneToOne
	@JoinColumn(name="USERNAME", referencedColumnName = "USERNAME")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getBhk() {
		return bhk;
	}

	public void setBhk(String bhk) {
		this.bhk = bhk;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
