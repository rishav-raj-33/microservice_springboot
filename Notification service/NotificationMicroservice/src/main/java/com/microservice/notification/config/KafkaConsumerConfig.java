package com.microservice.notification.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.microservice.notification.helpers.AppConstants;
import com.microservice.notification.helpers.InformationExtractor;
import com.microservice.notification.helpers.ProductNotification;
import com.microservice.notification.serviceClass.NotificationServiceClas;


@Configuration
public class KafkaConsumerConfig {
	
	@Autowired
	private NotificationServiceClas serviceClas;
	
	@Autowired
	private InformationExtractor extractor;

	
	@KafkaListener(topics=AppConstants.PRODUCT_TOPIC,groupId="group-1")
	public void Notify(String message) throws JsonMappingException, JsonProcessingException {
		
	try {
		ProductNotification notification=this.extractor.extractor(message);
		
		this.serviceClas.getNotification(notification);
		
		
	} catch (Exception e) {
		System.out.println( "error:"+e.getMessage());
		
	}	
	
	
			
	}
	

}