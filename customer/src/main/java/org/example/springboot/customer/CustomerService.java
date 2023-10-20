package org.example.springboot.customer;


import org.example.springboot.clients.fraud.FraudCheckResponse;
import org.example.springboot.clients.fraud.FraudClient;
import org.example.springboot.clients.notification.NotificationClient;
import org.example.springboot.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient, NotificationClient notificationClient) {

    public void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = Customer.builder()
                .firstName(registrationRequest.firstName())
                .lastName(registrationRequest.lastName())
                .email(registrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster())
            throw new IllegalStateException("OPS fraudster.....");

        notificationClient.sendNotification(new NotificationRequest(customer.getId(),customer.getEmail(),
                String.format("Hi %s, welcome to microservice",customer.getFirstName())));
    }
}
