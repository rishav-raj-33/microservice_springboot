package com.microservice.product.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends Exception {
	String resouceName;
	int fieldValue;
	String fieldName;
	public ResourceNotFoundException(String resouceName, String fieldName, int fieldValue) {
		super(String.format("%s not found with %s :%s",resouceName,fieldName,fieldValue));
		this.resouceName = resouceName;
		this.fieldValue = fieldValue;
		this.fieldName = fieldName;
	}

}
