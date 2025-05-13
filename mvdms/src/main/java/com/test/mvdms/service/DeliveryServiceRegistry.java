package com.test.mvdms.service;

import com.test.mvdms.service.impl.AmazonVendorDeliveryService;
import com.test.mvdms.service.impl.FlipkartVendorDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryServiceRegistry {

    private final Map<String, VendorDeliveryService> registry = new HashMap<>();

    @Autowired
    public DeliveryServiceRegistry(List<VendorDeliveryService> services) {
        services.forEach(service -> {
            if (service instanceof AmazonVendorDeliveryService) {
                registry.put("amazon", service);
            } else if (service instanceof FlipkartVendorDeliveryService) {
                registry.put("flipkart", service);
            }
        });
    }

    public VendorDeliveryService getService(String vendorName) {
        return registry.getOrDefault(vendorName, null);
    }
}

