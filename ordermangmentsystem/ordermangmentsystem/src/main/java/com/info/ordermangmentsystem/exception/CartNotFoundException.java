package com.info.ordermangmentsystem.exception;

public class CartNotFoundException extends BaseException {
    public CartNotFoundException(String message) {
        super(message, "Cart not found");
    }
}
