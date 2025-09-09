package com.salam.ecommerce.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service"
//        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    Integer reguestOrderPayment(@RequestBody PaymentRequest paymentRequest);

    @PostMapping("/payments")
    PaymentResponse pay(@RequestBody PaymentRequest request);
}
