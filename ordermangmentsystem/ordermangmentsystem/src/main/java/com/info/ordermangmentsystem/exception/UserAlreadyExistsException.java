package com.info.ordermangmentsystem.exception;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException(String message) {
        super(message, "User Already exits");
    }
}
