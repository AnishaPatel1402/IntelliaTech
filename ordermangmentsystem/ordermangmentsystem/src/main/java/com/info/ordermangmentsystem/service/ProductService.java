package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.ProductRequestDTO;
import com.info.ordermangmentsystem.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO request);

    ProductResponseDTO getProductById(Integer id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO updateStock(Integer productId, Integer newStock);
}
