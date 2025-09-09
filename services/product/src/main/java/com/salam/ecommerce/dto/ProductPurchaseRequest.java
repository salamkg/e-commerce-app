package com.salam.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull
        int productId,

        @NotNull
        double quantity
) {
}
