package com.info.ordermangmentsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class InvoiceResponseDTO {

    private String invoiceNumber;

    private Integer orderId;
    private String orderReference;

    private Integer userId;
    private String customerName;
    private String customerEmail;

    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;

    private List<InvoiceItemDTO> items;

    private Double subtotal;
    private Double discountAmount;
    private Double taxAmount;
    private Double finalAmount;

    private String paymentStatus;
}

