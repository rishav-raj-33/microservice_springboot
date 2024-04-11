package com.microservice.inventory.serviceClass;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.inventory.entity.InventoryEntity;
import com.microservice.inventory.helpers.InventoryDto;
import com.microservice.inventory.repository.InventoryRepo;
import com.microservice.inventory.service.InventoryService;

@Service
public class InventoryServiceClass implements InventoryService {
	
	
	@Autowired
	private InventoryRepo inventoryRepo;
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public InventoryDto updateStock(InventoryDto inventoryDto) {
		InventoryEntity stock=this.inventoryRepo.findByProductId(inventoryDto.getProductId());
		stock.setProductName(inventoryDto.getProductName());
		stock.setQnantity(inventoryDto.getQnantity());
			InventoryEntity updateStock=this.inventoryRepo.save(stock);
			return this.mapper.map(updateStock, InventoryDto.class);
	}

	@Override
	public InventoryDto addStock(InventoryDto inventoryDto) {
		InventoryEntity savedStock=this.inventoryRepo.save(this.mapper.map(inventoryDto, InventoryEntity.class));
		return this.mapper.map(savedStock, InventoryDto.class);
	}

	@Override
	public boolean DeleteStock(Integer productId) {
		InventoryEntity deleteStock=this.inventoryRepo.findByProductId(productId);
		this.inventoryRepo.delete(deleteStock);
		return true;
	}

}
