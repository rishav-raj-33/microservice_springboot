package com.microservice.product.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryStore {
	
	private Integer productId;
	private Integer qnantity;
	private String productName;

}
