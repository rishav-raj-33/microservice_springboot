package com.microservice.product.productController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.product.config.AppConstants;
import com.microservice.product.exceptions.ResourceNotFoundException;
import com.microservice.product.helpers.ApiResponse;
import com.microservice.product.helpers.InventoryStore;
import com.microservice.product.helpers.ProductDto;
import com.microservice.product.helpers.ProductNotification;
import com.microservice.product.helpers.ProductPageResponse;
import com.microservice.product.serviceClass.KafkaServiceClass;
import com.microservice.product.serviceClass.ProductServiceClass;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductControllers {
	
	@Autowired
	private ProductServiceClass serviceClass;
	@Autowired
	private KafkaServiceClass kafkaServiceClass;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@PostMapping("/")
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto ) throws ResourceNotFoundException, JsonProcessingException{
		ProductDto savedProduct=this.serviceClass.addProduct(productDto);
		String url="http://localhost:8080/api/inventory/";
		HttpHeaders headers=new HttpHeaders();
		headers.setBasicAuth(AppConstants.USER_NAME, AppConstants.PASSWORD);
		InventoryStore store=new InventoryStore(savedProduct.getProductId(), productDto.getQuantity(), productDto.getProductName());
		
		HttpEntity<InventoryStore> entity=new HttpEntity<>(store, headers);
		
		ResponseEntity<InventoryStore> getResponse=restTemplate.exchange(url,HttpMethod.POST,entity,InventoryStore.class);
		if(getResponse.getStatusCode().equals(HttpStatus.CREATED)) {
			ProductNotification notification=new ProductNotification(savedProduct.getProductName(), savedProduct.getProductModel(), savedProduct.getPrice());
			this.kafkaServiceClass.productRelease(notification);
			return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
		} else {
			this.serviceClass.deleteProduct(savedProduct.getProductId());
		}
			
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int id) throws ResourceNotFoundException{
		String url="http://localhost:8080/api/inventory/"+id;
		HttpHeaders headers=new HttpHeaders();
		headers.setBasicAuth(AppConstants.USER_NAME, AppConstants.PASSWORD);
		HttpEntity<?> entity=new HttpEntity<>(null,headers);
		ResponseEntity<String> response=this.restTemplate.exchange(url, HttpMethod.DELETE,entity,String.class);
		if (response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
			this.serviceClass.deleteProduct(id);
			return new ResponseEntity<>(new ApiResponse("Product Deleted", true),HttpStatus.NO_CONTENT);
			
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable int id) throws ResourceNotFoundException{
		String url="http://localhost:8080/api/inventory/update";
		HttpHeaders headers=new HttpHeaders();
		headers.setBasicAuth(AppConstants.USER_NAME, AppConstants.PASSWORD);
		ProductDto databaseProduct=this.serviceClass.searchProduct(id);
		ProductDto updateProduct=this.serviceClass.UpdateProduct(id, productDto);
		InventoryStore store=new InventoryStore(updateProduct.getProductId(), updateProduct.getQuantity(), updateProduct.getProductName());
		HttpEntity<InventoryStore> entity=new HttpEntity<>(store,headers);
		
		ResponseEntity<InventoryStore> getResponse=restTemplate.exchange(url,HttpMethod.PUT,entity,InventoryStore.class);
		if (getResponse.getStatusCode().equals(HttpStatus.ACCEPTED)) {
			return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.ACCEPTED);	
		}
		
		this.serviceClass.addProduct(databaseProduct);
		
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> searchProduct(@PathVariable int id) throws ResourceNotFoundException{
		ProductDto searchProduct=this.serviceClass.searchProduct(id);
		return new ResponseEntity<ProductDto>(searchProduct,HttpStatus.FOUND);
	}
	
	@GetMapping("/name/{model}")
	public ResponseEntity<ProductPageResponse> getProductByName(@RequestParam(value = "pageNumber" ,defaultValue =AppConstants.PAGE_NUMBER,required=false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "SortBy",defaultValue=AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir,@PathVariable String model ){
		ProductPageResponse response=this.serviceClass.getProductByName(model, pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<ProductPageResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ProductPageResponse> getAllProducts(@RequestParam(value = "pageNumber" ,defaultValue =AppConstants.PAGE_NUMBER,required=false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "SortBy",defaultValue=AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
		ProductPageResponse response=this.serviceClass.products(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<ProductPageResponse>(response,HttpStatus.OK);
	}
	@GetMapping("/category/{category}")
	public ResponseEntity<ProductPageResponse> getProductByCategory(@RequestParam(value = "pageNumber" ,defaultValue =AppConstants.PAGE_NUMBER,required=false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "SortBy",defaultValue=AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir,@PathVariable String category){
		    ProductPageResponse response=this.serviceClass.getProductByCategory(category, pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<ProductPageResponse>(response,HttpStatus.OK);
	}
	
	
	

}
