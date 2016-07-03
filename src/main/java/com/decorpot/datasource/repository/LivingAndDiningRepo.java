package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decorpot.datasource.models.LivingAndDining;

public interface LivingAndDiningRepo extends JpaRepository<LivingAndDining, Integer>{

	List<LivingAndDining> findAllByOrderByCreatedDateDesc();
}
