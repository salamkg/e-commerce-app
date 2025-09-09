package com.salam.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

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
