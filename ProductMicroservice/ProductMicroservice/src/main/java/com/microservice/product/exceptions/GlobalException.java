package com.microservice.product.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.microservice.product.helpers.ApiResponse;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse> resouceNotFoundExceptionHandler(ResourceNotFoundException exception){
		String message=exception.getMessage();
		ApiResponse response=new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> NotValidException(MethodArgumentNotValidException e){
		Map<String, String> errorResponse=new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String errorMessge=error.getDefaultMessage();
			
			errorResponse.put(fieldName, errorMessge);
			
		});	
				return new ResponseEntity<Map<String,String>>(errorResponse,HttpStatus.BAD_REQUEST);
	}

}
