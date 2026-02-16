package com.info.ordermangmentsystem.repository;

import com.info.ordermangmentsystem.entity.Cart;
import com.info.ordermangmentsystem.entity.CartItem;
import com.info.ordermangmentsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    List<CartItem> findByCart(Cart cart);
}
