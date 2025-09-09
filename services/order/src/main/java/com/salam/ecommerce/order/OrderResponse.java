package com.salam.ecommerce.order;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        Long customerId,
        Long productId,
        int quantity,
        String status
) {


}
