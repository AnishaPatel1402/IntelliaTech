package com.info.orderservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "order-events";

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(String orderId) {
        kafkaTemplate.send(TOPIC, orderId);
        System.out.println("Order event sent to Kafka: " + orderId);
    }
}
