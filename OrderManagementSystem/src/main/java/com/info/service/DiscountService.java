package com.info.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    public Double calculateDiscount(Double totalAmount) {
        if (totalAmount > 1000) {
            return totalAmount * 0.10; // 10% discount
        }
        return 0.0;
    }
}
