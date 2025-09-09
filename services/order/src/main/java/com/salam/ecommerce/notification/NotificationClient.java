package com.salam.ecommerce.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "notification-service"
)
public interface NotificationClient {

    @GetMapping("/notifications")
    void sendNotification(@RequestBody NotificationRequest request);
}
