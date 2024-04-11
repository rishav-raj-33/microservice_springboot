package com.microservice.product.helpers;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private Integer productId;
	@NotNull
	private String productModel;
	@NotNull
	private String productDescription;
	@NotNull
	private String productCategory;
	@NotNull
	private float price;
	@NotNull
	private String productName;
	
	@NotNull
	private Integer quantity;
}
