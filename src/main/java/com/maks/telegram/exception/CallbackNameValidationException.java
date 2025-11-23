package com.maks.telegram.exception;

public class CallbackNameValidationException extends RuntimeException {
    public CallbackNameValidationException(String message) {
        super(message);
    }
}
