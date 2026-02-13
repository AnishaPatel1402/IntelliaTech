package com.info.ordermangmentsystem.exception;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(String message) {
        super(message, "Product Not found");
    }
}
