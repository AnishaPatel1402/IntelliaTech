package com.info.ordermangmentsystem.dto;


import lombok.Data;

import java.util.List;

@Data
public class CartResponseDTO {
    private Integer userId;
    private List<CartItemResponseDTO> items;
    private Double grandTotal;
    private String status;
}
