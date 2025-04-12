package com.salam.ecommerce.orderline;

public record OrderLineRequest(
        Integer id,
        int orderId,
        int productId,
        double quantity
) {
}
