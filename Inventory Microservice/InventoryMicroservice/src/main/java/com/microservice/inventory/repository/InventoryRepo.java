package com.microservice.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.inventory.entity.InventoryEntity;

public interface InventoryRepo extends JpaRepository<InventoryEntity, Integer> {
	
	InventoryEntity findByProductId(Integer productId);

}
