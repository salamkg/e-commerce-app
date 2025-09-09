package com.salam.ecommerce.payment;

import com.salam.ecommerce.customer.CustomerResponse;
import com.salam.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Long orderId,
        BigDecimal amount
) {
}
