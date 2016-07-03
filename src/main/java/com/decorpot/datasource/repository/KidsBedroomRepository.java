package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decorpot.datasource.models.KidsBedroom;

@Repository
public interface KidsBedroomRepository extends JpaRepository<KidsBedroom, Integer>{

	public List<KidsBedroom> findByWardrobeTypeInOrderByCreatedDateDesc(List<String> wardrobeType);
	public List<KidsBedroom> findAllByOrderByCreatedDateDesc();
}