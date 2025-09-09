package com.salam.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        Long id,

        @NotNull
        String firstName,

        @NotNull
        String lastName,

        @NotNull
        @Email
        String email

) {
}
