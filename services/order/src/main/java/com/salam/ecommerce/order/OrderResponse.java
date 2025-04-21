package com.salam.ecommerce.order;

import java.math.BigDecimal;

public record OrderResponse(
        int id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {


}
