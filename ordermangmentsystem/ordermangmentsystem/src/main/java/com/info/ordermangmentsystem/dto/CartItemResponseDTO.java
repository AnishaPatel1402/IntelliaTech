package com.info.ordermangmentsystem.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {

    private Integer productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double totalPrice;
}