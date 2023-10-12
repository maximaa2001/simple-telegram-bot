package com.maks.telegram.exception;

public class UnknownParamsException extends Exception {
    public UnknownParamsException(String message) {
        super(message);
    }

    public UnknownParamsException(String message, Throwable cause) {
        super(message, cause);
    }
}
