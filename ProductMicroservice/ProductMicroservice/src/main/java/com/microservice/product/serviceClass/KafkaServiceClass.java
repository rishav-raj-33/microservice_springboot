package com.microservice.product.serviceClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.product.config.AppConstants;
import com.microservice.product.helpers.ProductNotification;
import com.microservice.product.service.KafkaService;

@Service
public class KafkaServiceClass implements KafkaService {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public boolean productRelease(ProductNotification notification) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(notification);
		this.kafkaTemplate.send(AppConstants.PRODUCT_TOPIC,json);
		return true;
	}

}
