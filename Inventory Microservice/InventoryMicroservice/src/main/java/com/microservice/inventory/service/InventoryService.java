package com.microservice.inventory.service;

import com.microservice.inventory.helpers.InventoryDto;

public interface InventoryService {
	
	InventoryDto updateStock(InventoryDto inventoryDto);
	
	InventoryDto addStock(InventoryDto inventoryDto);
	
	boolean DeleteStock(Integer productId);

}
