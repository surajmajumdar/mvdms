package com.test.mvdms.service;

import com.test.mvdms.dto.DeliveryRequest;
import com.test.mvdms.dto.DeliveryResponse;
import com.test.mvdms.dto.RetryResponse;
import org.springframework.stereotype.Service;

@Service
public interface VendorDeliveryService {

    DeliveryResponse scheduleDelivery(DeliveryRequest request);

    DeliveryResponse getDeliveryDetails(String deliveryId);

    RetryResponse retryFailedDelivery(String deliveryId);

}
