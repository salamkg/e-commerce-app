package com.salam.ecommerce.notification;

public record NotificationRequest(
        Long customerId,
        String message
) {
}
