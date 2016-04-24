package com.decorpot.datasource.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name= "APARTMENTS")
public class ApartmentConfig {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "APARTMENT_NAME")
    private String apartmentName;
    
    @Column(name = "CREATED_DATE", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date date;
    
    @Column(name = "ACTIVE")
    @Type(type = "yes_no")
    private boolean active;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }
  
}
