package com.microservice.notification.helpers;

public class InformationExtractor {
	
  public ProductNotification extractor(String information) {
		information=information.substring(information.indexOf(":")+2,information.length()-1);
	       String productName=information.substring(1,information.indexOf(",")-2);
	       information=information.substring(information.indexOf(":")+2,information.length());
	       String productModel=information.substring(1,information.indexOf(",")-2);
	       information=information.substring(information.indexOf(":"),information.length());
	       String price=information.substring(information.indexOf(":")+1,information.length()-1);
	       
	       float Productprice=Float.parseFloat(price);
	       
	       return new ProductNotification(productName, productModel, Productprice);
	}

}
