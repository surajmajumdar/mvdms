package com.test.mvdms.service.impl;

import com.test.mvdms.dto.*;
import com.test.mvdms.enums.Status;
import com.test.mvdms.exception.DeliveryNotFoundException;
import com.test.mvdms.model.Delivery;
import com.test.mvdms.repository.DeliveryRepository;
import com.test.mvdms.service.VendorDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("amazonService")
public class AmazonVendorDeliveryService implements VendorDeliveryService {

    private static final int MAX_RETRIES = 3;
    private static final double COST_PER_KG = 160;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DeliveryResponse scheduleDelivery(DeliveryRequest request) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime estimatedTime = now.plusHours(24);
        double cost = request.getPackageWeightKg() * COST_PER_KG;

        Delivery delivery = new Delivery(null, "userId", "deliveryId", request.getVendorName(),  request.getCustomerName(), request.getPickupLocation(),
                request.getDeliveryLocation(), Status.SCHEDULED, now, estimatedTime, "Cost: Rs. " + cost);
        deliveryRepository.save(delivery);

        return new DeliveryResponse(delivery.getDeliveryId(), "Amazon", request.getCustomerName(), request.getPickupLocation(),
                request.getDeliveryLocation(), Status.SCHEDULED, now, estimatedTime, "Scheduled successfully");
    }

    @Override
    public DeliveryResponse getDeliveryDetails(String deliveryId) {
        return deliveryRepository.findByDeliveryId(deliveryId)
                .map(d -> new DeliveryResponse(d.getDeliveryId(), d.getVendorName(), d.getCustomerName(), d.getPickupLocation(), d.getDeliveryLocation(), d.getStatus(), d.getScheduledAt(), d.getEstimatedDeliveryTime(), d.getMessage()))
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));    }

    @Override
    public RetryResponse retryFailedDelivery(String deliveryId) {
        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            if (attempt == MAX_RETRIES) {
                return new RetryResponse(deliveryId, Status.FAILED, "Amazon", "Retries exhausted");
            }
        }
        return new RetryResponse(deliveryId, Status.SCHEDULED, "Amazon", "Retry successful");
    }
}

