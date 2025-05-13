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

@Service("flipkartService")
public class FlipkartVendorDeliveryService implements VendorDeliveryService {

    private static final double FLAT_COST = 600;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DeliveryResponse scheduleDelivery(DeliveryRequest request) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime estimatedTime = now.plusHours(48);

        Delivery delivery = new Delivery(null, "userId", "deliveryId", request.getVendorName(),  request.getCustomerName(), request.getPickupLocation(),
                request.getDeliveryLocation(), Status.SCHEDULED, now, estimatedTime, "Flat Cost: Rs. " + FLAT_COST);
        deliveryRepository.save(delivery);

        return new DeliveryResponse(delivery.getDeliveryId(), "Flipkart", request.getCustomerName(), request.getPickupLocation(),
                request.getDeliveryLocation(), Status.SCHEDULED, now, estimatedTime, "Scheduled successfully");
    }

    @Override
    public DeliveryResponse getDeliveryDetails(String deliveryId) {
        return deliveryRepository.findByDeliveryId(deliveryId)
                .map(d -> new DeliveryResponse(d.getDeliveryId(), d.getVendorName(), d.getCustomerName(), d.getPickupLocation(), d.getDeliveryLocation(), d.getStatus(), d.getScheduledAt(), d.getEstimatedDeliveryTime(), d.getMessage()))
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));
    }

    @Override
    public RetryResponse retryFailedDelivery(String deliveryId) {
        return new RetryResponse(deliveryId, Status.CANCELLED, "Flipkart", "Auto-cancel on first failure");
    }
}



