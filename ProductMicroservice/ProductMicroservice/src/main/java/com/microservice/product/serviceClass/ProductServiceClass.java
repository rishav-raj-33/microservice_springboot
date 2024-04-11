package com.microservice.product.serviceClass;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservice.product.config.AppConstants;
import com.microservice.product.entity.ProductEntity;
import com.microservice.product.exceptions.ResourceNotFoundException;
import com.microservice.product.helpers.ProductDto;
import com.microservice.product.helpers.ProductPageResponse;
import com.microservice.product.repo.ProductRepo;
import com.microservice.product.service.ProductService;

@Service
public class ProductServiceClass implements ProductService {
	
	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ProductDto addProduct(ProductDto product) {
	ProductEntity productEntity=this.repo.save(this.mapper.map(product, ProductEntity.class));
		return this.mapper.map(productEntity, ProductDto.class);
	}

	@Override
	public void deleteProduct(Integer id) throws ResourceNotFoundException {
         ProductEntity productEntity=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product", "id",id));	
         this.repo.delete(productEntity);
	}

	@Override
	public ProductDto UpdateProduct(Integer id,ProductDto productDto) throws ResourceNotFoundException {
		ProductEntity productEntity=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product", "id",id));
		if (productDto.getPrice()!=0) {
			productEntity.setPrice(productDto.getPrice());
		}
		if (productDto.getProductCategory()!=null) {
			productEntity.setProductCategory(productDto.getProductCategory());
		}
		
		if (productDto.getProductDescription()!=null) {
			productEntity.setProductDescription(productDto.getProductDescription());
		}
		if (productDto.getProductModel()!=null) {
			productEntity.setProductModel(productDto.getProductModel());
		}
		if (productDto.getProductName()!=null) {
			productEntity.setProductName(productDto.getProductName());
		}
		if (productDto.getQuantity()!=null) {
			productEntity.setQuantity(productDto.getQuantity());
		}
		return this.mapper.map(this.repo.save(productEntity), ProductDto.class);
	}

	@Override
	public ProductDto searchProduct(Integer id) throws ResourceNotFoundException {
		ProductEntity productEntity=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product", "id",id));
		return this.mapper.map(productEntity, ProductDto.class);
	}

	@Override
	public ProductPageResponse products(int pageNumber, int pageSize, String sortBy, String sortDir){
		Sort sort=null;
		if(sortDir.equalsIgnoreCase(AppConstants.SORT_DIR)) {
			sort=Sort.by(sortBy).ascending();
		} else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
		Page<ProductEntity> pageProduct=this.repo.findAll(pageable);
		ProductPageResponse response=new ProductPageResponse();
		List<ProductDto> list=pageProduct.getContent().stream().map((obj)-> this.mapper.map(obj, ProductDto.class)).toList();
		response.setContent(list);
		response.setLastPage(pageProduct.isLast());
		response.setPageNumber(pageProduct.getNumber());
		response.setPageSize(pageProduct.getSize());
		response.setTotalElemnets(pageProduct.getTotalElements());
		response.setTotalPages(pageProduct.getTotalPages());
		return response;
	}

	@Override
	public ProductPageResponse getProductByName(String productName, int pageNumber, int pageSize, String sortBy,
			String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase(AppConstants.SORT_DIR)) {
			sort=Sort.by(sortBy).ascending();
		} else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
		Page<ProductEntity> pageProduct=this.repo.findByProductName(productName,pageable);
		ProductPageResponse response=new ProductPageResponse();
		List<ProductDto> list=pageProduct.getContent().stream().map((obj)-> this.mapper.map(obj, ProductDto.class)).toList();
		response.setContent(list);
		response.setLastPage(pageProduct.isLast());
		response.setPageNumber(pageProduct.getNumber());
		response.setPageSize(pageProduct.getSize());
		response.setTotalElemnets(pageProduct.getTotalElements());
		response.setTotalPages(pageProduct.getTotalPages());
		
		return response;
	}

	@Override
	public ProductPageResponse getProductByCategory(String Category, int pageNumber, int pageSize, String sortBy,
			String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase(AppConstants.SORT_DIR)) {
			sort=Sort.by(sortBy).ascending();
		} else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
		Page<ProductEntity> pageProduct=this.repo.findByProductCategory(Category,pageable);
		ProductPageResponse response=new ProductPageResponse();
		List<ProductDto> list=pageProduct.getContent().stream().map((obj)-> this.mapper.map(obj, ProductDto.class)).toList();
		response.setContent(list);
		response.setLastPage(pageProduct.isLast());
		response.setPageNumber(pageProduct.getNumber());
		response.setPageSize(pageProduct.getSize());
		response.setTotalElemnets(pageProduct.getTotalElements());
		response.setTotalPages(pageProduct.getTotalPages());
		return response;
	}



	
	
	

}
