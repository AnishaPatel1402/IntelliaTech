package com.info.ordermangmentsystem.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    public static Double calculateDiscount(Double totalAmount) {
        if (totalAmount > 10000) {
            return totalAmount * 0.10;
        }
        return 0.0;
    }
}

