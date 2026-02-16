package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.OrderItemResponseDTO;
import com.info.ordermangmentsystem.dto.OrderResponseDTO;
import com.info.ordermangmentsystem.entity.*;
import com.info.ordermangmentsystem.enums.CartStatus;
import com.info.ordermangmentsystem.exception.StockNotAvailableException;
import com.info.ordermangmentsystem.exception.UserNotFoundException;
import com.info.ordermangmentsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final ConcurrentHashMap<String, OrderResponseDTO> idempotencyStore = new ConcurrentHashMap<>();

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

        @Override
        public OrderResponseDTO placeOrder(Integer userId,String idempotencyKey) {

            try {
                UUID.fromString(idempotencyKey);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid Idempotency Key format");
            }
            return idempotencyStore.computeIfAbsent(idempotencyKey, key -> {

                User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

                Cart cart = cartRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException("Cart not found"));


                List<CartItem> cartItems = cartItemRepository.findByCart(cart);

                if (cartItems.isEmpty()) {
                    throw new RuntimeException("Cart is empty");
                }

                Order order = new Order();
                order.setUser(user);
                order.setOrderReference(generateOrderReference());
                order.setCreatedAt(LocalDateTime.now());
                order.setUpdatedAt(LocalDateTime.now());

                double totalAmount = 0.0;

                for (CartItem cartItem : cartItems) {

                    Product product = cartItem.getProduct();

                    if (product.getStock() < cartItem.getQuantity()) {
                        throw new StockNotAvailableException(
                                "Insufficient stock for product: "
                                        + product.getName());
                    }


                    product.setStock(product.getStock() - cartItem.getQuantity());
                    productRepository.save(product);

                    // Create OrderItem
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPriceAtOrder(product.getPrice());
                    orderItem.setCreatedAt(LocalDateTime.now());
                    orderItem.setUpdatedAt(LocalDateTime.now());

                    totalAmount += product.getPrice() * cartItem.getQuantity();

                    order.getOrderItems().add(orderItem);
                }

                order.setTotalAmount(totalAmount);
                order.setDiscountAmount(DiscountService.calculateDiscount(totalAmount));
                order.setFinalAmount(totalAmount);

                Order savedOrder = orderRepository.save(order);


            cartItemRepository.deleteAll(cartItems);
//                cart.setStatus(CartStatus.ORDERED);
//                cartRepository.save(cart);

                return buildOrderResponse(savedOrder);
            });
    }

    private String generateOrderReference() {
        return "ORD-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }


    @Override
    public List<OrderResponseDTO> getOrderHistory(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        List<Order> orders = orderRepository
                .findByUserIdOrderByCreatedAtDesc(userId);


        return orders.stream()
                .map(this::buildOrderResponse)
                .toList();
    }



    private OrderResponseDTO buildOrderResponse(Order order) {

        List<OrderItemResponseDTO> itemDTOs =
                order.getOrderItems()
                        .stream()
                        .map(item -> {
                            OrderItemResponseDTO dto =
                                    new OrderItemResponseDTO();
                            dto.setProductId(item.getProduct().getId());
                            dto.setProductName(item.getProduct().getName());
                            dto.setQuantity(item.getQuantity());
                            dto.setPriceAtOrder(item.getPriceAtOrder());
                            dto.setTotalPrice(item.getPriceAtOrder() * item.getQuantity());
                            return dto;
                        })
                        .toList();

        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setOrderReference(order.getOrderReference());
        response.setDiscountAmount(order.getDiscountAmount());
        response.setTotalAmount(order.getFinalAmount());
        response.setFinalAmount(order.getTotalAmount());
        response.setItems(itemDTOs);

        return response;
    }


}
