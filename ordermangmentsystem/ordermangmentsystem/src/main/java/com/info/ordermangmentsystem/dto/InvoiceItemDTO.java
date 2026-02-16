package com.info.ordermangmentsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceItemDTO {

    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}

