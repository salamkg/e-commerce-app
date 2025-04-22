package com.salam.ecommerce;


import com.salam.ecommerce.payment.Customer;
import com.salam.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
