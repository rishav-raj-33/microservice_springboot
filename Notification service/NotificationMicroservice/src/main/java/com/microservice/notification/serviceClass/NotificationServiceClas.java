package com.microservice.notification.serviceClass;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.notification.helpers.AppConstants;
import com.microservice.notification.helpers.CustomerDetails;
import com.microservice.notification.helpers.ProductNotification;
import com.microservice.notification.service.NotificationService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@Service
public class NotificationServiceClas implements NotificationService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private EmailSender emailSender;

	@Override
	public void getNotification(ProductNotification productNotification) {
		
		String url="http://localhost:8080/api/secure/customer/all";
		HttpHeaders headers=new HttpHeaders();
		headers.setBasicAuth(AppConstants.USER_NAME, AppConstants.PASSWORD);
		
		
		HttpEntity<String> entity=new HttpEntity<>(null, headers);
		ResponseEntity<List<CustomerDetails>> getResponse=restTemplate.exchange(url,HttpMethod.GET,entity, new ParameterizedTypeReference<List<CustomerDetails>>() {});
		List<CustomerDetails> customer=getResponse.getBody();
		Map<String, String> map=new HashMap<>();
		 customer.stream().map((obj)->map.put(obj.getEmail(), obj.getName())).toList();
		 sendMail(map,productNotification);	
	}

	@Override
	public void sendMail(Map<String, String> customerList,ProductNotification productNotification) {
		customerList.forEach((obj,obj1)->{
			try {
				String text=String.format("The %s (Model: %s now for %s, Grab the Limited Time Offer)",productNotification.getProductName(),
						productNotification.getProductModel(),Float.toString(productNotification.getPrice()));
				this.emailSender.sendEmail(obj, AppConstants.FROM, AppConstants.SUBJECT, text);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
		
		
	}

}