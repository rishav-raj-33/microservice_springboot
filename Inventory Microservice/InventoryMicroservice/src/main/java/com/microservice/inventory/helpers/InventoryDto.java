package com.microservice.inventory.helpers;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
	
	private Integer stockId;
	private Integer productId;
	private Integer qnantity;
	private String productName;
	
	

}
