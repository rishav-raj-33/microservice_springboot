package com.microservice.notification.service;

import java.util.Map;

import com.microservice.notification.helpers.ProductNotification;

public interface NotificationService {
	
	void getNotification(ProductNotification productNotification);
	
	void sendMail(Map<String, String> customerList,ProductNotification productNotification);

}