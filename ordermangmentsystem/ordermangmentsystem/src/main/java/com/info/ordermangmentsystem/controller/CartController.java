package com.info.ordermangmentsystem.controller;

import com.info.ordermangmentsystem.dto.CartRequestDTO;
import com.info.ordermangmentsystem.dto.CartResponseDTO;
import com.info.ordermangmentsystem.service.CartServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;

    @PostMapping
    public ResponseEntity<CartResponseDTO> addToCart(@Valid @RequestBody CartRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addToCart(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> viewCart(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(cartService.viewCart(userId));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartResponseDTO> removeCartItem(@RequestParam Integer userId, @RequestParam Integer productId) {

        return ResponseEntity.ok(
                cartService.removeCartItem(userId, productId)
        );
    }

}
