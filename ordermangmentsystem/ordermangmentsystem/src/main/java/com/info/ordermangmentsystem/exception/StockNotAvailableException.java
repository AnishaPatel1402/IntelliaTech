package com.info.ordermangmentsystem.exception;

public class StockNotAvailableException extends RuntimeException {
    public StockNotAvailableException(String message) {
        super(message);
    }
}

