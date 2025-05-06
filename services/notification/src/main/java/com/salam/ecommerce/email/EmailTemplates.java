package com.salam.ecommerce.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(final String template, final String subject) {
        this.template = template;
        this.subject = subject;
    }
}
