package com.info.ordermangmentsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReceiptResponseDTO {

    private String receiptNumber;
    private Integer orderId;
    private String invoiceNumber;

    private String transactionId;
    private String paymentMethod;

    private LocalDateTime paymentDate;

    private Double paidAmount;

    private String paymentStatus;
}
