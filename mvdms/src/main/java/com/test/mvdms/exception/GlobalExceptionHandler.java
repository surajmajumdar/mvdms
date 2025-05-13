package com.test.mvdms.exception;

import com.test.mvdms.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDeliveryNotFoundException(
            DeliveryNotFoundException ex) {
        logger.error("DeliveryNotFoundException: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("DELIVERY_NOT_FOUND");
        errorResponse.setMessage("No delivery found with given ID");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedAccessToDeliveryException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessToDeliveryException(
            UnauthorizedAccessToDeliveryException ex) {
        logger.error("UnauthorizedAccessToDeliveryException: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("ACCESS_DENIED");
        errorResponse.setMessage("Cannot view other's deliveries");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVendorNotFoundException(
            VendorNotFoundException ex) {
        logger.error("VendorNotFoundException: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("VENDOR_NOT_FOUND");
        errorResponse.setMessage("Vendor not registered");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnhandledException(
            Exception ex) {
        logger.error("Unhandled Exception: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("INTERNAL_SERVER_ERROR");
        errorResponse.setMessage("Something went wrong!");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
