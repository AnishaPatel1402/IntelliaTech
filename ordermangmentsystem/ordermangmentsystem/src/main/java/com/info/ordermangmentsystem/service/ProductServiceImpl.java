package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.ProductRequestDTO;
import com.info.ordermangmentsystem.dto.ProductResponseDTO;
import com.info.ordermangmentsystem.entity.Product;
import com.info.ordermangmentsystem.exception.ProductNotFoundException;
import com.info.ordermangmentsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {

        if (request.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (request.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Product saved = productRepository.save(product);

        return mapToDTO(saved);
    }

    @Override
    public ProductResponseDTO getProductById(Integer id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return mapToDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO updateStock(Integer productId, Integer newStock) {

        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.setStock(newStock);

        return mapToDTO(productRepository.save(product));
    }

    private ProductResponseDTO mapToDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
