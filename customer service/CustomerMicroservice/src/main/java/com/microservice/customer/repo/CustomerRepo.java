package com.microservice.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.customer.entity.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {

}
