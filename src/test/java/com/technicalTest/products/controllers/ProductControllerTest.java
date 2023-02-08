package com.technicalTest.products.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.technicalTest.products.exceptions.ResourceNotFoundException;
import com.technicalTest.products.model.Product;
import com.technicalTest.products.services.ProductService;

@SpringBootTest
public class ProductControllerTest {
    @Mock
    private ProductService productServiceTest;
    @InjectMocks
    private ProductController productContorllerTest;

       
    @Test
    public void shouldGetResponseOKWhenGetSimilarProductDetails() throws ResourceNotFoundException {
    	Product item1 = new Product("38","Blazer",29.99,true);
    	Product item2 = new Product("49","coat",49.99,true);
    	Product item3 = new Product("60","Trousers",89.99,true);
    	List<Product>  products = List.of(item1, item2, item3);
        
    	when(productServiceTest.getSimilarProductDetails("1")).thenReturn(products);
    	ResponseEntity<List<Product>> result = productContorllerTest.getProducts("1");
    	
    	assertEquals(result ,ResponseEntity.ok(products));      		

    }

}
