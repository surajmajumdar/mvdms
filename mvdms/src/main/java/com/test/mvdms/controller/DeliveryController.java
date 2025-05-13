package com.test.mvdms.controller;

import com.test.mvdms.dto.DeliveryRequest;
import com.test.mvdms.dto.DeliveryResponse;
import com.test.mvdms.dto.RetryResponse;
import com.test.mvdms.exception.VendorNotFoundException;
import com.test.mvdms.service.DeliveryServiceRegistry;
import com.test.mvdms.service.VendorDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class DeliveryController {

    @Autowired
    DeliveryServiceRegistry deliveryServiceRegistry;

    @PostMapping("/delivery")
    public ResponseEntity<DeliveryResponse> scheduleDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        VendorDeliveryService service = deliveryServiceRegistry.getService(deliveryRequest.getVendorName());
        if (service == null) {
            throw new VendorNotFoundException("Vendor not found");
        }
        return ResponseEntity.ok(service.scheduleDelivery(deliveryRequest));
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<DeliveryResponse> getDeliveryDetails(@PathVariable(name = "id") String deliveryId) {
        // Identify the vendor dynamically (e.g., from a database or mapping)
        String vendorName = "amazon";  // Replace with actual logic to get the vendor
        VendorDeliveryService service = deliveryServiceRegistry.getService(vendorName);
        if (service == null) {
            throw new VendorNotFoundException("Vendor not found");
        }
        return ResponseEntity.ok(service.getDeliveryDetails(deliveryId));
    }

    @PostMapping("/retry-failed-delivery/{id}")
    public ResponseEntity<RetryResponse> retryFailedDelivery(@PathVariable(name = "id") String deliveryId) {
        String vendorName = "amazon";  // Replace with actual logic to get the vendor
        VendorDeliveryService service = deliveryServiceRegistry.getService(vendorName);
        if (service == null) {
            throw new VendorNotFoundException("Vendor not found");
        }
        return ResponseEntity.ok(service.retryFailedDelivery(deliveryId));
    }
}
