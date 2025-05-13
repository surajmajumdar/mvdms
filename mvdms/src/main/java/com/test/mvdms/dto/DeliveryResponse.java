package com.test.mvdms.dto;

import com.test.mvdms.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeliveryResponse {
    String deliveryId;
    String vendorName;
    String customerName;
    String pickupLocation;
    String deliveryLocation;
    Status status;
    LocalDateTime scheduledAt;
    LocalDateTime estimatedDeliveryTime;
    String message;
}
