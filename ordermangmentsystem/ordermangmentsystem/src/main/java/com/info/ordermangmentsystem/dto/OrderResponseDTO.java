package com.info.ordermangmentsystem.dto;


import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDTO {

    private Integer orderId;
    private String orderReference;
    private Integer userId;

    private Double totalAmount;
    private Double discountAmount;
    private Double finalAmount;

    private List<OrderItemResponseDTO> items;
}

