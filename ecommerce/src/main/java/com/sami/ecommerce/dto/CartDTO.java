package com.sami.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private LocalDateTime createdAt;
    private List<CartItemDTO> cartItems;
    private Double totalPrice;

    public CartDTO(Long id, LocalDateTime createdAt, List<CartItemDTO> cartItems) {
        this.id = id;
        this.createdAt = createdAt;
        this.cartItems = cartItems;
        this.totalPrice = cartItems.stream()
                .mapToDouble(CartItemDTO::getSubtotal)
                .sum();
    }
}
