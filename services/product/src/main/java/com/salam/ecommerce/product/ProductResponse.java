package com.salam.ecommerce.product;

import java.math.BigDecimal;

public record ProductResponse(
        int id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        int categoryId,
        String categoryName,
        String categoryDescription
) {
}
