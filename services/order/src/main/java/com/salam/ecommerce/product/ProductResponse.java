package com.salam.ecommerce.product;

import java.math.BigDecimal;

public record ProductResponse(
        Long id, String name, int availableQuantity, BigDecimal price
) {
}
