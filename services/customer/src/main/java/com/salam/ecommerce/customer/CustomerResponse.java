package com.salam.ecommerce.customer;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {

}
