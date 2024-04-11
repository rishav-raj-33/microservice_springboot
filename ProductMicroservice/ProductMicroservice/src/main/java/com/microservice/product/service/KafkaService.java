package com.microservice.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.product.helpers.ProductNotification;

public interface KafkaService {
	
	boolean productRelease(ProductNotification notification)throws JsonProcessingException;

}
