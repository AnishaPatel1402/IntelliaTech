package com.info.paymentservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @KafkaListener(topics = "order-events", groupId = "payment-group")
    public void consumeOrder(String orderId) {
        System.out.println("Processing payment for order: " + orderId);

        System.out.println("Payment successful for order: " + orderId);
    }
}
