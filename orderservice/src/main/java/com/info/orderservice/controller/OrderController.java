package com.info.orderservice.controller;

import com.info.orderservice.entity.Order;
import com.info.orderservice.producer.OrderProducer;
import com.info.orderservice.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderController(OrderRepository orderRepository, OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setStatus("CREATED");
        Order savedOrder = orderRepository.save(order);
        orderProducer.sendOrderEvent("created order " + savedOrder.getId().toString());
        return savedOrder;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
