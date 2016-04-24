package com.decorpot.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.GuestBedroom;

@Repository
public interface GuestBedroomRepository extends JpaRepository<GuestBedroom, Integer>{

}
