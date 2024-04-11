package com.microservice.product.service;

import com.microservice.product.exceptions.ResourceNotFoundException;
import com.microservice.product.helpers.ProductDto;
import com.microservice.product.helpers.ProductPageResponse;

public interface ProductService {
	
ProductDto addProduct(ProductDto product);
	
	void deleteProduct(Integer id)throws ResourceNotFoundException;
	
	ProductDto UpdateProduct(Integer id,ProductDto productDto)throws ResourceNotFoundException;
	
	ProductDto searchProduct(Integer id)throws ResourceNotFoundException;
	
	ProductPageResponse products(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	ProductPageResponse getProductByName(String productName,int pageNumber,int pageSize,String sortBy,String sortDir);
	
	ProductPageResponse getProductByCategory(String Category,int pageNumber,int pageSize,String sortBy,String sortDir);

}
