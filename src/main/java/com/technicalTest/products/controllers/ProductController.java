package com.technicalTest.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.technicalTest.products.exceptions.ResourceNotFoundException;
import com.technicalTest.products.model.Product;
import com.technicalTest.products.services.ProductService;


@RestController
public class ProductController {
	@Autowired
    ProductService productService;
	@GetMapping("/product/{productId}/similar")
    public ResponseEntity<List<Product>> getProducts(@PathVariable String productId) throws ResourceNotFoundException {
   		
	 try {
		 return new ResponseEntity<List<Product>>(this.productService.getSimilarProductDetails(productId), HttpStatus.OK);
	 }catch(ResourceNotFoundException e) {
		 throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
	 }catch(Exception er) {
		 throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
	 }   	        
		
	}   

}