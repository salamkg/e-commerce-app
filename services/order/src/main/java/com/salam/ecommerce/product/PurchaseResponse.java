package com.salam.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        int productId,
        String description,
        BigDecimal price,
        double quantity
) {
}
