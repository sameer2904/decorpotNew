package com.decorpot.datasource.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SPACES_RANK")
public class Cart {
	
    @Id
    @Column(name = "RANK")
    private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
}
