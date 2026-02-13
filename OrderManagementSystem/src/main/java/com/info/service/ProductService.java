package com.info.service;

import org.springframework.stereotype.Service;

import com.info.entity.Product;
import com.info.exception.ProductNotFoundException;
import com.info.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	
    private final ProductRepository productRepository;
    
    public ProductService(ProductRepository productRepository) {
    	this.productRepository = productRepository;
    }


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
}
