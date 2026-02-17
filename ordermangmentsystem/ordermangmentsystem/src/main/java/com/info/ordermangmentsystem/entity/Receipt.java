package com.info.ordermangmentsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String receiptNumber;

    @Column(unique = true)
    private Long invoiceId;

    private Integer orderId;
    private Integer userId;

    private String invoiceNumber;

    private String transactionId;
    private String paymentMethod;

    private LocalDateTime paymentDate;

    private Double paidAmount;

    private String paymentStatus; // SUCCESS
}
