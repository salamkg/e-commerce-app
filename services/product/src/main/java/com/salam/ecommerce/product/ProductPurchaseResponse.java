package com.salam.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        int productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
