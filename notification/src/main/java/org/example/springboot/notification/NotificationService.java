package org.example.springboot.notification;

import org.example.springboot.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository notificationRepository) {

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder().
                        toCustomerId(notificationRequest.toCustomerId()).
                        toCustomerEmail(notificationRequest.toCustomerEmail()).
                        message(notificationRequest.message()).
                        sender("mba").
                        sentAt(LocalDateTime.now()).
                        build());
    }
}
