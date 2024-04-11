package com.microservice.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.customer.helper.CustomerDto;
import com.microservice.customer.serviceClass.CustomerServiceClass;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerServiceClass serviceClass;
	
	@PostMapping("/")
	public ResponseEntity<CustomerDto> registerUser(@Valid @RequestBody CustomerDto customer){
		return new ResponseEntity<>(this.serviceClass.addUser(customer),HttpStatus.CREATED);
	}
}
