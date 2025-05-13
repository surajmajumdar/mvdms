package com.test.mvdms.dto;

import com.test.mvdms.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetryResponse {
    String deliveryId;
    Status status;
    String vendorName;
    String message;
}
