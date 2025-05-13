package com.test.mvdms.exception;

public class UnauthorizedAccessToDeliveryException extends RuntimeException {
    public UnauthorizedAccessToDeliveryException(String message) {
        super(message);
    }
}
