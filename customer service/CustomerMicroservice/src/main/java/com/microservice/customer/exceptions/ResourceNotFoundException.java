package com.microservice.customer.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
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
