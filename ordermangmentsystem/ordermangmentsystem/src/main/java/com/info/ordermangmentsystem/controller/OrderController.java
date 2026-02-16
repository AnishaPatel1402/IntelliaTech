package com.info.ordermangmentsystem.controller;

import com.info.ordermangmentsystem.dto.OrderResponseDTO;
import com.info.ordermangmentsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place/{userId}")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestHeader("Idempotency-Key") String idempotencyKey,@PathVariable Integer userId) {

        return ResponseEntity.ok(orderService.placeOrder(userId, idempotencyKey));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderResponseDTO>> getOrderHistory(
            @RequestParam Integer userId) {

        return ResponseEntity.ok(
                orderService.getOrderHistory(userId)
        );
    }

}
