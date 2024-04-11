package com.microservice.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.customer.helper.ApiResponse;
import com.microservice.customer.helper.CustomerDto;
import com.microservice.customer.serviceClass.CustomerServiceClass;

@RestController
@RequestMapping("/api/secure/customer")
public class SecureController {
	@Autowired
	private CustomerServiceClass serviceClass;

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id){
		this.serviceClass.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer Deleted", true),HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CustomerDto>> allCustomers(){
		List<CustomerDto> list=this.serviceClass.getAllUser();
		return new ResponseEntity<List<CustomerDto>>(list,HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<CustomerDto> updateUser(@PathVariable int id,@RequestBody CustomerDto customer){
		CustomerDto updateCustomer=this.serviceClass.updateUser(customer, id);
		return new ResponseEntity<CustomerDto>(updateCustomer,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> searchUser(@PathVariable int id){
		CustomerDto searchCustomer=this.serviceClass.findUser(id);
		return new ResponseEntity<CustomerDto>(searchCustomer,HttpStatus.FOUND);
	}
	
	
	
	
	
	
}
