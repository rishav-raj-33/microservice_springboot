package com.microservice.product.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	@Bean
	 NewTopic topic() {
		return TopicBuilder.name(AppConstants.PRODUCT_TOPIC).build();
		
	}
	
}
