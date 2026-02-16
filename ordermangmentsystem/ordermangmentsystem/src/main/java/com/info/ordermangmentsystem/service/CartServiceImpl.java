package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.CartItemResponseDTO;
import com.info.ordermangmentsystem.dto.CartRequestDTO;
import com.info.ordermangmentsystem.dto.CartResponseDTO;
import com.info.ordermangmentsystem.entity.Cart;
import com.info.ordermangmentsystem.entity.CartItem;
import com.info.ordermangmentsystem.entity.Product;
import com.info.ordermangmentsystem.entity.User;
import com.info.ordermangmentsystem.enums.CartStatus;
import com.info.ordermangmentsystem.exception.ProductNotFoundException;
import com.info.ordermangmentsystem.exception.StockNotAvailableException;
import com.info.ordermangmentsystem.exception.UserNotFoundException;
import com.info.ordermangmentsystem.repository.CartItemRepository;
import com.info.ordermangmentsystem.repository.CartRepository;
import com.info.ordermangmentsystem.repository.ProductRepository;
import com.info.ordermangmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public CartResponseDTO addToCart(CartRequestDTO request) {

        if (request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }


        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));


        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));


        if (product.getStock() < request.getQuantity()) {
            throw new StockNotAvailableException("Not enough stock available");
        }


        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setStatus(CartStatus.ACTIVE);
                    newCart.setCreatedAt(LocalDateTime.now());
                    newCart.setUpdatedAt(LocalDateTime.now());
                    return cartRepository.save(newCart);
                });


        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElse(null);

        if (cartItem != null) {

            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.setUpdatedAt(LocalDateTime.now());

        } else {

            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setCreatedAt(LocalDateTime.now());
            cartItem.setUpdatedAt(LocalDateTime.now());
        }

        cartItemRepository.save(cartItem);

        return buildCartResponse(cart);
    }


    @Override
    @Transactional(readOnly = true)
    public CartResponseDTO viewCart(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Cart is empty"));

        return buildCartResponse(cart);
    }



    @Override
    public CartResponseDTO removeCartItem(Integer userId, Integer productId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElseThrow(() ->
                        new RuntimeException("Product not in cart"));


        cartItemRepository.delete(cartItem);

        return buildCartResponse(cart);
    }



    private CartResponseDTO buildCartResponse(Cart cart) {

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);

        List<CartItemResponseDTO> itemDTOs = cartItems.stream()
                .map(item -> {
                    CartItemResponseDTO dto = new CartItemResponseDTO();
                    dto.setProductId(item.getProduct().getId());
                    dto.setProductName(item.getProduct().getName());
                    dto.setPrice(item.getProduct().getPrice());
                    dto.setQuantity(item.getQuantity());
                    dto.setTotalPrice(
                            item.getProduct().getPrice() * item.getQuantity()
                    );
                    return dto;
                })
                .toList();

        double grandTotal = itemDTOs.stream()
                .mapToDouble(CartItemResponseDTO::getTotalPrice)
                .sum();

        CartResponseDTO response = new CartResponseDTO();
        response.setUserId(cart.getUser().getId());
        response.setItems(itemDTOs);
        response.setGrandTotal(grandTotal);
        response.setStatus(cart.getStatus().toString());

        return response;
    }
}
