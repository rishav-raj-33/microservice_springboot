package com.microservice.customer.serviceClass;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.customer.entity.CustomerEntity;
import com.microservice.customer.exceptions.ResourceNotFoundException;
import com.microservice.customer.helper.CustomerDto;
import com.microservice.customer.repo.CustomerRepo;
import com.microservice.customer.service.CustomerService;

@Service
public class CustomerServiceClass implements CustomerService {
	
	@Autowired
	private CustomerRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public CustomerDto addUser(CustomerDto customer) {
		
		CustomerEntity customerEntity=this.modelMapper.map(customer, CustomerEntity.class);
		customerEntity.setPasword(this.encoder.encode(customer.getPasword()));
		return this.modelMapper.map(this.repo.save(customerEntity), CustomerDto.class);
	}

	@Override
	public CustomerDto updateUser(CustomerDto customer, int id) {
		
		CustomerEntity searchCustomer=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id" , id));
		if(customer.getName()!=null) {
			searchCustomer.setName(customer.getName());
		}
		
		if(customer.getPasword()!=null) {
			searchCustomer.setPasword(this.encoder.encode(customer.getPasword()));
		}
		if(customer.getEmail()!=null) {
			searchCustomer.setEmail(customer.getEmail());
		}
		return this.modelMapper.map(searchCustomer, CustomerDto.class);
	}

	@Override
	public CustomerDto findUser(int id) {
		CustomerEntity searchCustomer=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id" , id));
		return this.modelMapper.map(searchCustomer,CustomerDto.class);
	}

	@Override
	public void deleteUser(int id) {
		CustomerEntity customer=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id" , id));
           this.repo.delete(customer);		
	}

	@Override
	public List<CustomerDto> getAllUser() {
		List<CustomerEntity> customerEntities=this.repo.findAll();
		List<CustomerDto> customerDtos=customerEntities.stream().map((obj)->this.modelMapper.map(obj, CustomerDto.class)).toList();
		return customerDtos;
	}

}
