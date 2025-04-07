package com.salam.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull
        int productId,

        @NotNull
        double quantity
) {
}
