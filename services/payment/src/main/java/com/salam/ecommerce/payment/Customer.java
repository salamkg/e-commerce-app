package com.salam.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,

        @NotNull
        String firstname,

        @NotNull
        String lastname,

        @NotNull
        @Email
        String email
) {
}
