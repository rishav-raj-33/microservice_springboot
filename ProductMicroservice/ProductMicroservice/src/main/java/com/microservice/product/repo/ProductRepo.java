package com.microservice.product.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.product.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
	
	Page<ProductEntity> findByProductCategory(String productCategory,Pageable pageable);
	Page<ProductEntity> findByProductName(String productName,Pageable pageable);

}
