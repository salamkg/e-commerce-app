package com.salam.ecommerce.kafka;

import com.salam.ecommerce.customer.CustomerResponse;
import com.salam.ecommerce.order.PaymentMethod;
import com.salam.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
