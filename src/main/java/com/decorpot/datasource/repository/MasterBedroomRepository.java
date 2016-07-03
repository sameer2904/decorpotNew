package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.MasterBedroom;

@Repository
public interface MasterBedroomRepository extends JpaRepository<MasterBedroom, Integer>{
	
	public List<MasterBedroom> findByWardrobeTypeInOrderByCreatedDateDesc(List<String> wardrobeType);
	public List<MasterBedroom> findAllByOrderByCreatedDateDesc();
	
}
