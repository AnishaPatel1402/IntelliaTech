package com.info.ordermangmentsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String invoiceNumber;

    @Column(unique = true)
    private Integer orderId;

    private Integer userId;

    private String orderReference;

    private String customerName;
    private String customerEmail;

    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;

    private Double subtotal;
    private Double discountAmount;
    private Double taxAmount;
    private Double finalAmount;

    private String paymentStatus;
}

