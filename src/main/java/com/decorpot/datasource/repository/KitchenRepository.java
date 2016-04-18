package com.decorpot.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.Kitchen;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Integer>{

}
