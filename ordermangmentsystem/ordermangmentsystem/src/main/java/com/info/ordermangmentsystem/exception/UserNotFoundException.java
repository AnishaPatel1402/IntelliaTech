package com.info.ordermangmentsystem.exception;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message, "USER Not found");
    }
}
