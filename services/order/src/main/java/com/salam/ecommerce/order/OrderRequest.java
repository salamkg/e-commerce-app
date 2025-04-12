package com.salam.ecommerce.order;

import com.salam.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive()
        BigDecimal amount,

        @NotNull
        PaymentMethod paymentMethod,

        @NotNull
        @NotBlank
        @NotEmpty
        String customerId,

        @NotEmpty
        List<PurchaseRequest> products
) {
}
