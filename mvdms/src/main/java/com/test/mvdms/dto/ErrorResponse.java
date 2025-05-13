package com.test.mvdms.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    String errorCode;
    String message;
}
