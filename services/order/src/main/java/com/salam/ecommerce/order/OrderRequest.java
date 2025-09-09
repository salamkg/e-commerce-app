package com.salam.ecommerce.order;

public record OrderRequest(
        Long customerId,
        Long productId,
        int quantity
) {
}
