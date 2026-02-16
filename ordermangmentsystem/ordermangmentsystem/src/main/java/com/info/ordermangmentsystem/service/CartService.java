package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.CartRequestDTO;
import com.info.ordermangmentsystem.dto.CartResponseDTO;

public interface CartService {
    CartResponseDTO addToCart(CartRequestDTO request);
    CartResponseDTO viewCart(Integer userId);
    CartResponseDTO removeCartItem(Integer userId, Integer productId);
}