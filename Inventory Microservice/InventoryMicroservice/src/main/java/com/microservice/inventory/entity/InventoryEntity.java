package com.microservice.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class InventoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer stockId;
	private Integer productId;
	private Integer qnantity;
	private String productName;

}
