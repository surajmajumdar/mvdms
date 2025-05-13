package com.test.mvdms.dto;

import lombok.Data;

@Data
public class DeliveryRequest {
    String vendorName;
    String customerName;
    String pickupLocation;
    String deliveryLocation;
    Double packageWeightKg;
}
