package com.info.ordermangmentsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptRequestDTO {

    private Integer invoiceId;
    private String transactionId;
    private String paymentMethod;
}
