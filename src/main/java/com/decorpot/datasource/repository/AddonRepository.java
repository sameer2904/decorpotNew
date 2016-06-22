package com.decorpot.datasource.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.decorpot.datasource.models.Addon;

public interface AddonRepository extends CrudRepository<Addon, Integer>{
	
	public List<Addon> findByParentIdAndSpace(int parentId, String space);

}
