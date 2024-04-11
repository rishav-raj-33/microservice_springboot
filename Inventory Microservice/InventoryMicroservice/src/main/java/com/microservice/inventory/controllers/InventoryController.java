package com.microservice.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.inventory.helpers.InventoryDto;
import com.microservice.inventory.serviceClass.InventoryServiceClass;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryServiceClass serviceClass;
	
	
	@PostMapping("/")
	public ResponseEntity<InventoryDto> addStock(@RequestBody InventoryDto inventoryDto){
		InventoryDto addStock=this.serviceClass.addStock(inventoryDto);
		return new ResponseEntity<InventoryDto>(addStock,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<InventoryDto> updateStock(@RequestBody InventoryDto inventoryDto){
		InventoryDto updateInventoryDto=this.serviceClass.updateStock(inventoryDto);
		return new ResponseEntity<InventoryDto>(updateInventoryDto,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteStock(@PathVariable Integer productId){
		this.serviceClass.DeleteStock(productId);
		return new ResponseEntity<String>("Deleted",HttpStatus.NO_CONTENT);
	}

}
