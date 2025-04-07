package com.salam.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        int id,
        @NotNull
        String name,

        @NotNull
        String description,

        @Positive
        double availableQuantity,

        @Positive
        BigDecimal price,

        @NotNull
        int categoryId
) {
}
