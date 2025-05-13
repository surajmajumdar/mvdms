package com.test.mvdms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.test.mvdms")
public class MultiVendorDeliveryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiVendorDeliveryManagementSystemApplication.class, args);
	}

}
