package com.info.ordermangmentsystem.dto;

import lombok.Data;

@Data
public class OrderItemResponseDTO {

    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double priceAtOrder;
    private Double totalPrice;
}

