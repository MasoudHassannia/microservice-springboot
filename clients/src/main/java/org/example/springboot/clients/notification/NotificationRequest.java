package org.example.springboot.clients.notification;

public record NotificationRequest(Integer toCustomerId,String toCustomerEmail,String message) {

}
