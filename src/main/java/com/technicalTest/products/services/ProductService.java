package com.technicalTest.products.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.technicalTest.products.exceptions.ResourceNotFoundException;
import com.technicalTest.products.model.Product;

@Service
public class ProductService {
	
	private static final String BASE_URL = "http://localhost:3001/product/";
	private static final String PATH = "/similarids";

	
	public List<Product> getSimilarProductDetails(String productId) throws ResourceNotFoundException{
		List<Product> productDetails = new ArrayList<>();
		
		if(null != productId) {
			List<String> mockProductIds =  getMockProductIds(productId);
			
			if(null != mockProductIds) {
				
				for(String id : mockProductIds) {
						Product product = getMockProductDetails(id);
						productDetails.add(product);				
					
				}		
				
			}else {
				throw new ResourceNotFoundException("A null value was obtained for id of similar products");
				
			}
		}		
		return productDetails;	
		
	}
	public List<String> getMockProductIds (String productId) throws ResourceNotFoundException {
		RestTemplate restTemplate = new RestTemplate();
		List<String> mockProductIds = new ArrayList<>();
		try {	
			mockProductIds = Arrays.asList(restTemplate.getForObject(BASE_URL + productId + PATH, String[].class));
		} catch (Exception e) {
			throw new ResourceNotFoundException("An error occurred while getting the ids of similar products");
		}
		
		return mockProductIds;		
	}
	
	public Product getMockProductDetails (String productId) throws ResourceNotFoundException {
		RestTemplate restTemplate = new RestTemplate();		
		Product productDetail = null;		
		
		try{
			productDetail = restTemplate.getForObject(BASE_URL + productId, Product.class);
			
		} catch (Exception e) {
			throw new ResourceNotFoundException("An error occurred while getting the details of similar products");
		}
		return productDetail;			
	}
 }
