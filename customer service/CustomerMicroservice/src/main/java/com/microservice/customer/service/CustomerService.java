package com.microservice.customer.service;

import java.util.List;

import com.microservice.customer.helper.CustomerDto;

public interface CustomerService {
	
	CustomerDto addUser(CustomerDto customer);
	CustomerDto updateUser(CustomerDto customer,int id);
	CustomerDto findUser(int id);
	void deleteUser(int id);
	List<CustomerDto> getAllUser();
}
