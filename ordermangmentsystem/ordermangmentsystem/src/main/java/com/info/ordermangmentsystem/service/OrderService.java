package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO placeOrder(Integer userId, String idempotencyKey);
    List<OrderResponseDTO> getOrderHistory(Integer userId);
}
