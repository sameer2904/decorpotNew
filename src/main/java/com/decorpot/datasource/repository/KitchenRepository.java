package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.Kitchen;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Integer>{
	
	public List<Kitchen> findAllByOrderByCreatedDateDesc();
	public List<Kitchen> findByKitchenTypeOrderByCreatedDateDesc(String kitchenType);
}
