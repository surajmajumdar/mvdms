package com.test.mvdms.model;

import com.test.mvdms.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;
    private String deliveryId;
    private String vendorName;
    private String customerName;
    private String pickupLocation;
    private String deliveryLocation;
    private Status status;
    private LocalDateTime scheduledAt;
    private LocalDateTime estimatedDeliveryTime;
    private String message;

}
